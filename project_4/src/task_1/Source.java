package task_1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Source {
	private String id;
	private String name;
	
	@JsonCreator
	private Source(@JsonProperty("id") String id, @JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Gets id of article source.
	 * 
	 * @return id as String
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gets name of article source.
	 * 
	 * @return name as String
	 */
	public String getName() {
		return name;
	}
}
