
/* Uygulama Parametreleri */
INSERT INTO applicationparameters
	(parametername, parametervalue, datetime)
	VALUES ('application.appToken', 'MIX_gsger4859didzXER_REwpurts_77', now()),
	('login.clearSessions.runInterval.minutes', '1', now()),
	('login.session.maxIdleTimeout.minutes', '100', now()),
	('cache.refreshInterval.minutes', '1', now()),
	('sql.query.Timeout', '60000', now()),
	('http.timeout', '60000', now());