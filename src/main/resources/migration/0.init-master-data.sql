-- INIT COUNTRY DATA
insert into public.tb_countries (id, created_date, lastmodified_date, alpha2_code, alpha3_code, name, status, createdby_id, lastmodifiedby_id)
values (1, '2022-10-17 00:38:38.000000', '2022-10-17 00:40:24.000000', 'FR', 'FRA', 'France', 'ACTIVE', 1, 1);

-- INIT ADMIN USER DATA
insert into public.tb_appusers (id, nonexpired, nonlocked, cannot_change_password, nonexpired_credentials, is_deleted, email, enabled, firsttime_login_remaining, firstname, gender, is_self_service_user, last_time_password_updated, lastname, password, password_never_expires, username, country_id)
values (1, true, true, false, true, false, 'admin@gmail.com', true, true, 'ADMIN', 0, false, null, 'ADMIN', '$2a$10$1M4qP1cfmhnOnDJqpFoqbuVVSg3g7NCJKXqzSsepr71c6N2vn7YIO', true, 'ADMIN', 1);
