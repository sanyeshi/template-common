package org.template.common.util;

import java.io.Serializable;

import org.junit.Test;

import com.template.common.exception.JsonSerializationException;
import com.template.common.util.JsonUtil;

public class JsonUtilTest {

	@Test
	public void toJsonTest() throws JsonSerializationException {
		User user = new User(1L, "ssl");
		String json = JsonUtil.toJson(user);
		System.out.println(json);
	}

	static class User implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long id;
		private String name;

		public User(Long id, String name) {
			this.id = id;
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}

	}
}
