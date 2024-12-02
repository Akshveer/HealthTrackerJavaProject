document.addEventListener("DOMContentLoaded", () => {
    // Check if userId is stored in sessionStorage first
    let userId = sessionStorage.getItem('loggedInUserId');
    
    // If userId is not found in sessionStorage, check the URL query parameters
    if (!userId) {
        const urlParams = new URLSearchParams(window.location.search);
        userId = urlParams.get('userId');
        
        // If userId is found in the URL, store it in sessionStorage for future use
        if (userId) {
            sessionStorage.setItem('loggedInUserId', userId);
        }
    }

    // If userId is still not found, log an error and return
    if (!userId) {
        console.error("User not logged in");
        return;
    }

    // Dynamically fetch health metrics for the logged-in user
    fetch(`/metrics/${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch health metrics');
            }
            return response.json();
        })
        .then(data => {
            console.log("Fetched Health Metrics:", data); // Debug log for fetched data
            
            if (!data || data.length === 0) {
                console.error("No data available to display charts");
                return;
            }

            // Extract data for charts
            const labels = data.map(metric => metric.date); // Dates for X-axis
            const stepsData = data.map(metric => metric.steps); // Steps for bar chart
            const weightData = data.map(metric => metric.weight); // Weight for pie chart (example)

            // Create Bar Chart for Daily Steps
            const barChartCtx = document.getElementById('barChart').getContext('2d');
            new Chart(barChartCtx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Steps',
                        data: stepsData,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1,
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true,
                            ticks: {
                                color: '#fff',
                            }
                        },
                        x: {
                            ticks: {
                                color: '#fff',
                            }
                        }
                    },
                    plugins: {
                        legend: {
                            labels: {
                                color: '#fff',
                            }
                        }
                    }
                }
            });

            // Create Pie Chart for Weight Distribution (example)
            const pieChartCtx = document.getElementById('pieChart').getContext('2d');
            new Chart(pieChartCtx, {
                type: 'pie',
                data: {
                    labels: labels, // Dates as labels
                    datasets: [{
                        label: 'Weight (kg)',
                        data: weightData,
                        backgroundColor: [
                            '#ff6384', '#36a2eb', '#ffcd56',
                            '#4bc0c0', '#9966ff', '#ff9f40',
                        ],
                        borderColor: '#1a1a1a',
                        borderWidth: 1,
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                            labels: {
                                color: '#fff',
                            }
                        }
                    }
                }
            });
        })
        .catch(error => {
            console.error("Error fetching or displaying health metrics:", error);
        });
});
