TradePal
TradePal is a simple Android application that allows users to input a stock ticker symbol and retrieve relevant information about the stock, such as its current price, point change, percent change, volume, 52-week range, PEG ratio, mean target price, and a verdict on whether to buy, hold, wait, or short the stock based on certain conditions.

Features
Enter a stock ticker symbol to retrieve information.
Display current price, point change, percent change, volume, 52-week range, PEG ratio, and mean target price.
Verdict on whether to buy, hold, wait, or short the stock based on specific conditions.
Uses two different APIs to gather stock information.
Installation
Clone the repository to your local machine.
Open the project in Android Studio.
Build and run the app on an Android device or emulator.
Usage
Launch the TradePal app on your Android device.
Enter a valid stock ticker symbol in the input field.
Press the "Go" button to retrieve information about the stock.
The app will display the stock's current price, point change, percent change, volume, 52-week range, PEG ratio, mean target price, and a verdict based on certain conditions.
Dependencies
OkHttp: Used for making HTTP requests to the APIs.
Gson: Used for JSON parsing.
API Usage
TradePal uses two different APIs to gather stock information:

RealStonks API: Provides stock information, including price, change percentage, change point, total volume, etc.

yfinance-stock-market-data API: Provides additional stock information, such as 52-week high, 52-week low, PEG ratio, and mean target price.

License
This project is licensed under the MIT License - see the LICENSE.md file for details.
