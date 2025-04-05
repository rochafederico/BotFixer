# BotFixer - API de mejora de mensajes para bots

**BotFixer** es una API basada en Spring Boot que tiene como objetivo mejorar la comunicación entre los usuarios y los bots de conversación.

## Funcionalidades

- Endpoint básico para verificar que la API esté funcionando (`GET /api/bot/status`).
- Endpoint POST para recibir y procesar mensajes del usuario.
- Resumen usando utilizando [OpenNLP Models](https://opennlp.apache.org/models.html).

## Cosas por hacer

1. **Agregar más modelos NLP** (pendiente integrar Stanford CoreNLP para análisis de emociones).
2. **Conectar con una base de datos** para almacenar y mejorar los mensajes procesados.
3. **Mejorar la lógica** para generar frases más precisas y claras.
4. **Implementar validaciones** de entradas y manejo de errores.

## Contribuir

Si deseas contribuir, abre un *issue* o haz un *fork* y envía un *pull request*.
