package com.xinmao.common.consultantCenter.domain;

/**
 * User: 李志坚
 * Date: 2018/12/25
 * 咨询方式枚举类
 */
public enum ConsultationMethodEnum {
	VOICE(1,"语音"),
	VIDEO(2,"视频"),
	FACE_TO_FACE(3,"面对面"),
	VIDEO_OR_FACE_TO_FACE(4,"视频/当面");

	private Integer key;

	private String value;

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	ConsultationMethodEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public static String getValueByKey(Integer key) {
		ConsultationMethodEnum[] enums = values();
		for (ConsultationMethodEnum consultationMethodEnum : enums) {
			if (consultationMethodEnum.getKey().equals(key)) {
				return consultationMethodEnum.getValue();
			}
		}
		return "";
	}
}
