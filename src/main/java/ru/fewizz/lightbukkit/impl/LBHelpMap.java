package ru.fewizz.lightbukkit.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicFactory;

public class LBHelpMap implements HelpMap {
	Map<String, HelpTopic> topics = new HashMap<>();

	@Override
	public HelpTopic getHelpTopic(String topicName) {
		return topics.get(topicName);
	}

	@Override
	public Collection<HelpTopic> getHelpTopics() {
		return Collections.unmodifiableCollection(topics.values());
	}

	@Override
	public void addTopic(HelpTopic topic) {
		topics.put(topic.getName(), topic);
	}

	@Override
	public void clear() {
		topics.clear();
	}

	@Override
	public void registerHelpTopicFactory(Class<?> commandClass, HelpTopicFactory<?> factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getIgnoredPlugins() {
		return Collections.EMPTY_LIST;
	}

}
