package com.mockito.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class User {
	private String uname;
	private String pass;
}
