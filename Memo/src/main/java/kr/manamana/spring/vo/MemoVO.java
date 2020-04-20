package kr.manamana.spring.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoVO {
	private int 	idx;
	private String 	name;
	private String 	password;
	private String 	content;
	private Date	regDate;
	private String 	ip;
}
