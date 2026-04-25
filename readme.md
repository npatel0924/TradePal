# TradePal

A simple Android app for quick stock lookups. Enter a ticker, get the key numbers, and see a rule-based verdict on whether to **buy**, **hold**, **wait**, or **short** — all in one screen.

## Features

- Look up any stock by ticker symbol
- Pulls live data from two complementary stock APIs
- Displays current price, point change, percent change, and volume
- Shows 52-week range, PEG ratio, and mean analyst target price
- Generates a buy / hold / wait / short verdict from the retrieved metrics

## Tech stack

- **Languages:** Kotlin and Java
- **Build system:** Gradle (Kotlin DSL)
- **Networking:** [OkHttp](https://square.github.io/okhttp/) for HTTP calls
- **JSON parsing:** [Gson](https://github.com/google/gson)

## APIs used

TradePal combines two sources to get the full picture on a ticker:

| API | Provides |
| --- | --- |
| RealStonks | Current price, point change, percent change, total volume |
| yfinance-stock-market-data | 52-week high/low, PEG ratio, mean target price |

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/npatel0924/TradePal.git
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync, then build and run on a connected device or emulator.

You may need to provide your own API keys for the upstream services depending on their current access requirements — check the relevant request code in the `app/` module.

## Usage

1. Launch TradePal on your Android device.
2. Type a valid ticker symbol (e.g. `AAPL`, `MSFT`, `NVDA`) into the input field.
3. Tap **Go**.
4. The app displays:
   - Current price, point change, percent change, and volume
   - 52-week range, PEG ratio, and mean target price
   - A buy / hold / wait / short verdict based on the retrieved values

## Project structure

```
TradePal/
├── app/                 # Android application module
├── gradle/wrapper/      # Gradle wrapper
├── build.gradle.kts     # Top-level build config
├── settings.gradle.kts
└── README.md
```

## Disclaimer

TradePal is a personal project for learning Android development and working with public stock APIs. The verdict it generates is based on simple rules over a handful of metrics — it is **not** financial advice, and should not be used as the sole basis for any investment decision.

## License

This project is licensed under the MIT License — see `LICENSE.md` for details.
