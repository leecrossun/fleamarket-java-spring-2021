package com.yanado.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.yanado.dto.Alarm;


@Mapper
@Transactional
public interface AlarmDAO {

	// Alarm CRUD
	void insertAlarm(Alarm alarm);

	Alarm findAlarmByAlarmId(String alarmId);
	
	List<Alarm> findAlarmByCommonId(String commonId);
	
	List<Alarm> findAlarmByAucId(String aucId);

	List<Alarm> findAlarmByAuctionId(String aucId);
	
	void deleteAllCommonAlarm(String commonId);
	
	void deleteAllAcuAlarm(String aucId);

	// MyPage
	List<Alarm> findAllAlarmByUserId(String userId);
}
