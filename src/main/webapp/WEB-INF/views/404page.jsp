<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .content {
            text-align: center;
        }

        .error-code {
            font-size: 6rem;
            font-weight: bold;
            color: #333;
        }

        .error-msg {
            font-size: 1.5rem;
            color: #555;
            margin-bottom: 1rem;
        }

        .home-link {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="content">
        <h1 class="error-code">404</h1>
        <p class="error-msg">Oops! The page you're looking for could not be found.</p>
        <p>Go back to the <a class="home-link" href="<c:url value='/trang-chu'/> ">home page</a>.
            <span id="timer">5</span> seconds...
        </p>
    </div>
</div>

<script>
    // Countdown timer
    var count = 5;
    var timerElement = document.getElementById('timer');

    var countdown = setInterval(function() {
        count--;
        timerElement.textContent = count;

        if (count === 0) {
            clearInterval(countdown);
            window.location.href = "/";
        }
    }, 1000);
</script>
</body>
</html>