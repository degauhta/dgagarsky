package ru.job4j;

/**
 * Vacancy class.
 * Created on 25.07.2017.
 */
class Vacancy {
    /**
     * Topic date.
     */
    private String topicDate;

    /**
     * Topic.
     */
    private String topic;

    /**
     * Full description.
     */
    private String fullDescription;

    /**
     * Url.
     */
    private String url;

    /**
     * Default constructor.
     *
     * @param topicDate       topic date
     * @param topic           topic
     * @param fullDescription full description
     * @param url             url
     */
    Vacancy(String topicDate, String topic, String fullDescription, String url) {
        this.topicDate = topicDate;
        this.topic = topic;
        this.fullDescription = fullDescription;
        this.url = url;
    }

    /**
     * Get topic date.
     * @return topic date
     */
    String getTopicDate() {
        return topicDate;
    }

    /**
     * Get topic.
     * @return topic
     */
    String getTopic() {
        return topic;
    }

    /**
     * Get full description.
     * @return full description
     */
    String getFullDescription() {
        return fullDescription;
    }

    /**
     * Get url.
     * @return url
     */
    String getUrl() {
        return url;
    }
}
