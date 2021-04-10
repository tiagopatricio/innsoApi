package com.home.app.model.types;

public enum Channels {
	
	MAIL("MAIL"),
	SMS("SMS"),
	FACEBOOK("FACEBOOK"),
	TWITTER("TWITTER");
	
	private String name;

	private Channels(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static Channels getChannelByName(String name) {
		if (name == null) {
			return null;
		}
		for(Channels channel : Channels.values()) {
			if (channel.getName().equals(name)) {
				return channel;
			}
		}
		return null;
	}

}
