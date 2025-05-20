<%--
  Created by IntelliJ IDEA.
  User: Aline
  Date: 18/05/2025
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Remover Contas</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    form { max-width: 500px; margin: 0 auto; }
    label { display: block; margin-bottom: 5px; }
    input[type="text"] { width: 100%; padding: 8px; margin-bottom: 15px; }
    button { padding: 10px 15px; background-color: #dc3545; color: white; border: none; cursor: pointer; }
    button:hover { background-color: #c82333; }
  </style>
</head>
<body>
<h1>Remover Múltiplas Contas</h1>
<form action="removerContas" method="post">
  <label for="ids">IDs das contas a remover (separados por vírgula):</label>
  <input type="text" id="ids" name="ids" placeholder="Ex: 1, 2, 3" required>
  <p><strong>Atenção:</strong> Esta operação não pode ser desfeita.</p>
  <button type="submit">Remover Contas</button>
</form>
</body>
</html>
