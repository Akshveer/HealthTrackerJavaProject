document.addEventListener("DOMContentLoaded", () => {
    
	
	// Fetch the user ID from the session (assumes you're setting it in the session on login)
    const userId = sessionStorage.getItem('loggedInUserId');
    
    // Extract the userId from the URL query parameter
    const urlParams = new URLSearchParams(window.location.search);
    const userIdFromUrl = urlParams.get('userId');
    
    // Use the userId from URL if not found in sessionStorage
    const finalUserId = userId || userIdFromUrl;
	
    if (!finalUserId) {
        console.error("User not logged in");
        return; // Don't proceed if user ID is not available
    }
    
    // Dynamically fetch health metrics for the logged-in user
    fetch(`/metrics/${finalUserId}`)
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
            const weightData = data.map(metric => metric.weight); // Weight data for line chart
            const stepsData = data.map(metric => metric.steps); // Steps data for histogram
            const sleepData = data.map(metric => metric.sleepHours); // Sleep data for gauge chart
    
            // Create Line Chart for Weight
            const lineChartCtx = document.getElementById('lineChart').getContext('2d');
            new Chart(lineChartCtx, {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Weight (kg)',
                        data: weightData,
                        borderColor: 'rgba(75, 192, 192, 1)',
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        fill: true,
                        borderWidth: 2,
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: false,
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
    
            // Create Histogram for Steps Walked
            const histogramCtx = document.getElementById('histogramChart').getContext('2d');
            new Chart(histogramCtx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Steps Walked',
                        data: stepsData,
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        borderColor: 'rgba(255, 99, 132, 1)',
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
    
            // Create Gauge Chart for Sleep Cycle using Plotly
            const gaugeChartDiv = document.getElementById('gaugeChart');
            const sleepValue = sleepData[sleepData.length - 1]; // Get the latest sleep data
    
            const gaugeData = [{
                type: "indicator",
                mode: "gauge+number",
                value: sleepValue,
                title: { text: "Sleep Hours", font: { size: 24 } },
                gauge: {
                    axis: { range: [0, 10], tickwidth: 1, tickcolor: "black" },
                    bar: { color: "rgba(0, 255, 0, 0.7)" },
                    bgcolor: "transparent",  // Set background to transparent
                    borderwidth: 2,
                    bordercolor: "white"
                }
            }];
    
            const layout = {
                paper_bgcolor: 'transparent',  // Set background color of the entire plot to transparent
                plot_bgcolor: 'transparent',   // Set background color of the chart's plotting area to transparent
                font: { color: 'white' },      // Set the font color for the labels and numbers
                margin: { t: 50, r: 50, b: 50, l: 50 }, // Adjust margins
            };
    
            Plotly.newPlot(gaugeChartDiv, gaugeData, layout);
    
            // Add event listeners for zoom buttons after chart creation
            const zoomLineChartBtn = document.getElementById('zoomLineChartBtn');
            const zoomHistogramChartBtn = document.getElementById('zoomHistogramChartBtn');
    
            if (zoomLineChartBtn && zoomHistogramChartBtn) {
                zoomLineChartBtn.addEventListener('click', function() {
                    toggleZoom('lineChartContainer');
                });

                zoomHistogramChartBtn.addEventListener('click', function() {
                    toggleZoom('histogramChartContainer');
                });
            }
    
            // Zoom functionality
            function toggleZoom(chartId) {
                const chartContainer = document.getElementById(chartId);
                
                // Toggle zoom: if the chart is small, make it larger, and vice versa
                if (chartContainer.style.transform === 'scale(1.5)') {
                    chartContainer.style.transform = 'scale(1)';
                    chartContainer.style.zIndex = '1'; // Reset z-index
                } else {
                    chartContainer.style.transform = 'scale(1.5)';
                    chartContainer.style.zIndex = '10'; // Bring the chart to the front
                }
            }
        })
        .catch(error => {
            console.error("Error fetching or displaying health metrics:", error);
        });
		
		document.addEventListener("DOMContentLoaded", () => {
		    const addTodaysGainsButton = document.getElementById('addTodaysGains');
		    if (addTodaysGainsButton) {
		        addTodaysGainsButton.addEventListener('click', () => {
		            window.location.href = "/health-metrics.html";
		        });
		    } else {
		        console.error("Add Today's Gains button not found in DOM");
		    }
		});

		document.getElementById('addTodaysGains').addEventListener('click', () => {
		    window.location.href = "/health-metrics.html";
		});

		
});


