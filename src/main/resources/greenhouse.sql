database : greenhouse_system

CREATE TABLE `greenhouse_monitor_data` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`greenhouse_id` INT(11) NOT NULL DEFAULT '0' COMMENT '温室id',
	`airtemp` DOUBLE(10,4) NOT NULL DEFAULT '0.0000' COMMENT '空气温度',
	`airhumi` DOUBLE(10,4) NOT NULL DEFAULT '0.0000' COMMENT '空气湿度',
	`soiltemp` DOUBLE(10,4) NOT NULL DEFAULT '0.0000' COMMENT '土壤温度',
	`soilhumi` DOUBLE(10,4) NOT NULL DEFAULT '0.0000' COMMENT '土壤湿度',
	`co2` DOUBLE(10,4) NOT NULL DEFAULT '0.0000' COMMENT '二氧化碳浓度',
	`sundata` DOUBLE(10,4) NOT NULL DEFAULT '0.0000' COMMENT '光照强度',
	`datetime` DATETIME NULL DEFAULT NULL COMMENT '日期时间',
	PRIMARY KEY (`id`)
)
COMMENT='温室监测数据'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;