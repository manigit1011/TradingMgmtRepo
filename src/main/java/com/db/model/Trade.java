package com.db.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Trade")

public class Trade implements Comparable<Trade> {
	public Trade() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "trade_id")
	private String tradeId;

	@Column(name = "version")
	private Integer version;

	@Column(name = "cp_id")
	private String counterPartyId;

	@Column(name = "book_id")
	private String bookId;

	@Column(name = "maturity_date")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date maturityDate;

	@Column(name = "created_date")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date createdDate;

	@Column(name = "expired")
	private Boolean expired;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean isExpired() {
		return expired;
	}

	public Trade(String tradeId, Integer version, String counterPartyId, String bookId, Date maturityDate,
			Date createdDate, Boolean expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public int compareTo(Trade trade) {
		return trade.getVersion().compareTo(this.getVersion());
	}


}
