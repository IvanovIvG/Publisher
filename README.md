Приложение запускается через docker-compose

Состоит из 2х сервисов:
-Бэк приложения, работает на 8080 порту
-Сервер авторизации, работает на 9090 порту

Swagger документация доступна по запросу /doc

Приложение защищено OAuth2.
Данные для клиента:
-clientId=frontend
-clientSecret=frontSecret              
-redirectUri=https://localhost:3000/authorized
-scope=openid
-authURL=http://localhost:9090/oauth2/authorize
-accessTokenURL=http://localhost:9090/oauth2/token

Зарегестрированный пользователь в системе:
-login=bob
-password=abc
