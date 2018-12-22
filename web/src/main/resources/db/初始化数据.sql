delete from UserInfo;
INSERT INTO UserInfo(IsDisabled, Birthday, IsSystem, Sex, Password, UserName, UserCode, UserId)
VALUES(0, null, 1, 0, '0d640a406596c708629a7824d34d0e39', '系统管理员', 'admin', '7EC205C2-E157-4036-98C1-58D0DD2817CC');
INSERT INTO UserInfo(IsDisabled, Birthday, IsSystem, Sex, Password, UserName, UserCode, UserId)
VALUES(0, null, 0, 0, '25c278f2b306449559f6b79b36c7b2e9', '曾军毅', 'zjy', '45b90fb3-d794-4f8f-b0f6-f4744cb9a704');

delete from Menu;
INSERT INTO Menu(icon, url, seq, code, name, pId, menuId)
VALUES('fa fa-cog', NULL, 1, 'admin', '后台管理', NULL, '9730a5dc-b084-4b03-83b3-e6da247b3165');
INSERT INTO Menu(icon, url, seq, code, name, pId, menuId)
VALUES('fa fa-user', '/user/user', 1, 'user', '用户管理', '9730a5dc-b084-4b03-83b3-e6da247b3165', 'c552baec-0c44-40de-b150-e1399d67b34d');
INSERT INTO Menu(icon, url, seq, code, name, pId, menuId)
VALUES('fa fa-cog', '/menu/list', 2, 'menu', '功能管理', '9730a5dc-b084-4b03-83b3-e6da247b3165', '0a0cde01-a512-4c17-a6d1-117ffe086e58');
