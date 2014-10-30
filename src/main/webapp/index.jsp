<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Client</title>
        <link rel="stylesheet" href="/airline/style.css">
    </head>
    <body>
        <div id="container">
            <nav>
                <ul>
                    <li>
                        <a id="createFlight" href="#createFlight">Create flight</a>
                    </li>
                    <li>
                        <a id="listFlights" href="#listFlights">List flights</a>
                    </li>
                    <li>
                        <a id="createDestination" href="#createDestination">Create destination</a>
                    </li>
                    <li>
                        <a id="listDestinations" href="#listDestinations">List destinations</a>
                    </li>
                    <li>
                        <a id="createReservation" href="#createReservation">Create reservation</a>
                    </li>
                    <li>
                        <a id="listReservations" href="#listReservations">List reservations</a>
                    </li>
                </ul>
            </nav>
            <div id="content"></div>
        </div>
        <script src="/airline/jquery-1.11.1.min.js"></script>
        <script src="/airline/script.js"></script>
    </body>
</html>