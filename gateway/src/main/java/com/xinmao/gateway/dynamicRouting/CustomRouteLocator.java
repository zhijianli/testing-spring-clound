package com.xinmao.gateway.dynamicRouting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.cloud.netflix.zuul.filters.discovery.SimpleServiceRouteMapper;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import com.xinmao.gateway.domain.ZuulRouteMessage;
import com.xinmao.gateway.mapper.ZuulRouteMapper;

/**
 * 动态路由器，系统启动时加载
 * @author 李志坚
 * @date 2018/2/2.
 */
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator{

	private static final Logger logger = LogManager.getLogger();
	
	public static final String DEFAULT_ROUTE = "/**";
	
    @Autowired
    private ZuulRouteMapper mapper;
	
//	private DiscoveryClient discovery;

    private ZuulProperties properties;
    
    private ServiceRouteMapper serviceRouteMapper;

    public CustomRouteLocator(String servletPath, DiscoveryClient discovery, ZuulProperties properties) {
        super(servletPath, properties);
           
        
        this.properties = properties;
        logger.info("servletPath:{}",servletPath);
    }
    
	public CustomRouteLocator(String servletPath, DiscoveryClient discovery,
			ZuulProperties properties, ServiceRouteMapper serviceRouteMapper) {
		this(servletPath, discovery, properties);
		this.serviceRouteMapper = serviceRouteMapper;
	}

	public void addRoute(String path, String location) {
		this.properties.getRoutes().put(path, new ZuulRoute(path, location));
		refresh();
	}

	public void addRoute(ZuulRoute route) {
		this.properties.getRoutes().put(route.getPath(), route);
		refresh();
	}

    //父类已经提供了这个方法，这里写出来只是为了说明这一个方法很重要！！！
//    @Override
//    protected void doRefresh() {
//        super.doRefresh();
//    }


    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
        
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        
        //从db中加载路由信息
        routesMap.putAll(locateRoutesFromDB());
        
        logger.error(routesMap);
        
        
        //优化一下配置
        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulRoute> locateRoutesFromDB(){
        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        
        
        //获取数据库的路由数据
//        List<ZuulRouteMessage> results = jdbcTemplate.query("select * from gateway_api_define where enabled = true ",new BeanPropertyRowMapper<>(ZuulRouteMessage.class));
        
        List<ZuulRouteMessage> results =  mapper.selectAll();
        
        
        for (ZuulRouteMessage result : results) {
            if(org.apache.commons.lang.StringUtils.isBlank(result.getPath())){
//            	|| org.apache.commons.lang3.StringUtils.isBlank(result.getUrl())
                continue;
            }
            ZuulRoute zuulRoute = new ZuulRoute();
            try {
                org.springframework.beans.BeanUtils.copyProperties(result,zuulRoute);
            } catch (Exception e) {
                logger.error("=============load zuul route info from db with error==============",e);
            }
            routes.put(zuulRoute.getPath(),zuulRoute);
        }
        return routes;
    }
    
	protected String mapRouteToService(String serviceId) {
		return this.serviceRouteMapper.apply(serviceId);
	}

	
	protected void addConfiguredRoutes(Map<String, ZuulRoute> routes) {
		Map<String, ZuulRoute> routeEntries = this.properties.getRoutes();
		for (ZuulRoute entry : routeEntries.values()) {
			String route = entry.getPath();
			if (routes.containsKey(route)) {
				logger.warn("Overwriting route " + route + ": already defined by "
						+ routes.get(route));
			}
			routes.put(route, entry);
		}
	}

}
