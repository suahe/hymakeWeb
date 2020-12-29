package com.hymake.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description t_chronology 表映射实体类
 * @author daizb@hymake.com
 * @date 2020-07-03 13:48:53
 * @version 1.0
 * @remark create by ca
 */

public class ChronologyEntity {

	/** 年表编号 */
	private long chronologyId;
	/** 时间 */
	private Date occurrenceTime;
	/** 内容 */
	private String content;
	/** 更新人 */
	private String updateUserid;
	/** 更新时间 */
	private Date updateTime;
	
	/** 查询时间开始 */
	private Date occurrenceTimeStart;
	/** 查询时间结束 */
	private Date occurrenceTimeEnd;
	/** 年表编号 */
	public long getChronologyId(){
		return chronologyId;
	}

	/** 年表编号 */
	public void setChronologyId(long chronologyId){
		this.chronologyId = chronologyId;
	}
	
	/** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	public Date getOccurrenceTime(){
		return occurrenceTime;
	}

	/** 时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
	public void setOccurrenceTime(Date occurrenceTime){
		this.occurrenceTime = occurrenceTime;
	}
	
	/** 内容 */
	public String getContent(){
		return content;
	}

	/** 内容 */
	public void setContent(String content){
		this.content = content;
	}
	
	/** 更新人 */
	public String getUpdateUserid(){
		return updateUserid;
	}

	/** 更新人 */
	public void setUpdateUserid(String updateUserid){
		this.updateUserid = updateUserid;
	}
	
	/** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getUpdateTime(){
		return updateTime;
	}

	/** 更新时间 */
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getOccurrenceTimeStart() {
		return occurrenceTimeStart;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setOccurrenceTimeStart(Date occurrenceTimeStart) {
		this.occurrenceTimeStart = occurrenceTimeStart;
	}

	public Date getOccurrenceTimeEnd() {
		return occurrenceTimeEnd;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setOccurrenceTimeEnd(Date occurrenceTimeEnd) {
		this.occurrenceTimeEnd = occurrenceTimeEnd;
	}

	public ChronologyEntity(){
		
	}
	
	/**
	 * @param chronologyId  年表编号
	 * @param occurrenceTime  时间
	 * @param content  内容
	 * @param updateUserid  更新人
	 * @param updateTime  更新时间
	 */
	public ChronologyEntity(long chronologyId, Date occurrenceTime, String content, String updateUserid, Date updateTime){
	
		this.chronologyId = chronologyId;
		this.occurrenceTime = occurrenceTime;
		this.content = content;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
	}
}