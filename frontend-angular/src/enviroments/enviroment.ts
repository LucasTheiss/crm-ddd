export const environment = {
  production: false,
  
  // O Java está exposto na porta 8081 no docker-compose
  apiUrl: 'http://localhost:8081/api',
  
  // URL para a conexão de WebSocket (tempo real do chat)
  wsUrl: 'ws://localhost:8081/ws',
  
  // (Opcional) Se o front precisar bater direto em alguma rota do Gateway .NET
  gatewayUrl: 'http://localhost:5000/api' 
};