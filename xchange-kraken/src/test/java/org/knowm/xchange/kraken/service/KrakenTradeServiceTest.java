package org.knowm.xchange.kraken.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.kraken.dto.trade.TimeInForce;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.orders.DefaultOpenOrdersParamCurrencyPair;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.knowm.xchange.currency.CurrencyPair.BTC_USD;
import static org.knowm.xchange.currency.CurrencyPair.LTC_USD;
import static org.knowm.xchange.currency.CurrencyPair.XBT_USD;

public class KrakenTradeServiceTest extends BaseWiremockTest {

	public static final LimitOrder LIMIT_ORDER =
			new LimitOrder(
					Order.OrderType.BID,
					new BigDecimal("2.12340000"),
					XBT_USD,
					null,
					null,
					new BigDecimal("45000.1"));
	private static final String OPEN_ORDERS_BODY =
			"""
					{
					  "error": [],
					  "result": {
					    "open": {
					      "OQCLML-BW3P3-BUCMWZ": {
					        "refid": null,
					        "userref": 0,
					        "status": "open",
					        "opentm": 1616666559.8974,
					        "starttm": 0,
					        "expiretm": 0,
					        "descr": {
					          "pair": "XBTUSD",
					          "type": "buy",
					          "ordertype": "limit",
					          "price": "30010.0",
					          "price2": "0",
					          "leverage": "none",
					          "order": "buy 1.25000000 XBTUSD @ limit 30010.0",
					          "close": ""
					        },
					        "vol": "1.25000000",
					        "vol_exec": "0.37500000",
					        "cost": "11253.7",
					        "fee": "0.00000",
					        "price": "30010.0",
					        "stopprice": "0.00000",
					        "limitprice": "0.00000",
					        "misc": "",
					        "oflags": "fciq",
					        "trades": [
					          "TCCCTY-WE2O6-P3NB37"
					        ]
					      },
					      "OB5VMB-B4U2U-DK2WRW": {
					        "refid": null,
					        "userref": 120,
					        "status": "open",
					        "opentm": 1616665899.5699,
					        "starttm": 0,
					        "expiretm": 0,
					        "descr": {
					          "pair": "LTCUSD",
					          "type": "buy",
					          "ordertype": "limit",
					          "price": "14500.0",
					          "price2": "0",
					          "leverage": "5:1",
					          "order": "buy 0.27500000 LTCUSD @ limit 14500.0 with 5:1 leverage",
					          "close": ""
					        },
					        "vol": "0.27500000",
					        "vol_exec": "0.00000000",
					        "cost": "0.00000",
					        "fee": "0.00000",
					        "price": "0.00000",
					        "stopprice": "0.00000",
					        "limitprice": "0.00000",
					        "misc": "",
					        "oflags": "fciq"
					      },
					      "OXHXGL-F5ICS-6DIC67": {
					        "refid": null,
					        "userref": 120,
					        "status": "open",
					        "opentm": 1616665894.0036,
					        "starttm": 0,
					        "expiretm": 0,
					        "descr": {
					          "pair": "LTCUSD",
					          "type": "buy",
					          "ordertype": "limit",
					          "price": "17500.0",
					          "price2": "0",
					          "leverage": "5:1",
					          "order": "buy 0.27500000 LTCUSD @ limit 17500.0 with 5:1 leverage",
					          "close": ""
					        },
					        "vol": "0.27500000",
					        "vol_exec": "0.00000000",
					        "cost": "0.00000",
					        "fee": "0.00000",
					        "price": "0.00000",
					        "stopprice": "0.00000",
					        "limitprice": "0.00000",
					        "misc": "",
					        "oflags": "fciq"
					      },
					      "OLQCVY-B27XU-MBPCL5": {
					        "refid": null,
					        "userref": 251,
					        "status": "open",
					        "opentm": 1616665556.7646,
					        "starttm": 0,
					        "expiretm": 0,
					        "descr": {
					          "pair": "XBTUSD",
					          "type": "buy",
					          "ordertype": "limit",
					          "price": "23500.0",
					          "price2": "0",
					          "leverage": "none",
					          "order": "buy 0.27500000 XBTUSD @ limit 23500.0",
					          "close": ""
					        },
					        "vol": "0.27500000",
					        "vol_exec": "0.00000000",
					        "cost": "0.00000",
					        "fee": "0.00000",
					        "price": "0.00000",
					        "stopprice": "0.00000",
					        "limitprice": "0.00000",
					        "misc": "",
					        "oflags": "fciq"
					      },
					      "OQCGAF-YRMIQ-AMJTNJ": {
					        "refid": null,
					        "userref": 0,
					        "status": "open",
					        "opentm": 1616665511.0373,
					        "starttm": 0,
					        "expiretm": 0,
					        "descr": {
					          "pair": "XBTUSD",
					          "type": "buy",
					          "ordertype": "limit",
					          "price": "24500.0",
					          "price2": "0",
					          "leverage": "none",
					          "order": "buy 1.25000000 XBTUSD @ limit 24500.0",
					          "close": ""
					        },
					        "vol": "1.25000000",
					        "vol_exec": "0.00000000",
					        "cost": "0.00000",
					        "fee": "0.00000",
					        "price": "0.00000",
					        "stopprice": "0.00000",
					        "limitprice": "0.00000",
					        "misc": "",
					        "oflags": "fciq"
					      }
					    }
					  }
					}""";
	private static final String ORDER_HISTORY_BODY =
			"""
					{
					  "error": [],
					  "result": {
					    "trades": {
					      "THVRQM-33VKH-UCI7BS": {
					        "ordertxid": "OQCLML-BW3P3-BUCMWZ",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616667796.8802,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30010.00000",
					        "cost": "600.20000",
					        "fee": "0.00000",
					        "vol": "0.02000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TCWJEG-FL4SZ-3FKGH6": {
					        "ordertxid": "OQCLML-BW3P3-BUCMWZ",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616667769.6396,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30010.00000",
					        "cost": "300.10000",
					        "fee": "0.00000",
					        "vol": "0.01000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TCCCTY-WE2O6-P3NB37": {
					        "ordertxid": "OQCLML-BW3P3-BUCMWZ",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616666586.6077,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30010.00000",
					        "cost": "11253.75000",
					        "fee": "0.00000",
					        "vol": "0.37500000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TZX2WP-XSEOP-FP7WYR": {
					        "ordertxid": "OBCMZD-JIEE7-77TH3F",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616665496.7842,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30021.00000",
					        "cost": "37526.25000",
					        "fee": "37.52500",
					        "vol": "1.25000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TJUW2K-FLX2N-AR2FLU": {
					        "ordertxid": "OMMDB2-FSB6Z-7W3HPO",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616592012.2334,
					        "type": "sell",
					        "ordertype": "market",
					        "price": "30000.00000",
					        "cost": "7500.00000",
					        "fee": "7.50000",
					        "vol": "0.25000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TKTFJU-O5OIY-63ZBF4": {
					        "ordertxid": "OFWNMX-VIOHE-HCYVU6",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616591594.5669,
					        "type": "sell",
					        "ordertype": "limit",
					        "price": "30000.00000",
					        "cost": "15000.00000",
					        "fee": "15.00000",
					        "vol": "0.50000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TRJPJP-6VYIP-5XLJZA": {
					        "ordertxid": "O3XCSX-SLY3X-LGHPI3",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616522002.4217,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30014.00000",
					        "cost": "37517.50000",
					        "fee": "37.51592",
					        "vol": "1.25000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TSALPW-Q3HPI-LEPCOA": {
					        "ordertxid": "O6355P-ESPHJ-PEXFBY",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616521990.7427,
					        "type": "sell",
					        "ordertype": "limit",
					        "price": "30000.00000",
					        "cost": "150000.00000",
					        "fee": "150.00000",
					        "vol": "5.00000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TQ7WSQ-U3R7T-ZUMFIY": {
					        "ordertxid": "ONZOB7-3ESLV-IQ2PZT",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616521380.6707,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30013.43386",
					        "cost": "37516.79233",
					        "fee": "37.51606",
					        "vol": "1.25000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "T42BLA-LGJHI-LPVTTD": {
					        "ordertxid": "O3DZBO-5SI2M-S6WCNU",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616521336.9991,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30013.00000",
					        "cost": "37516.25000",
					        "fee": "37.51597",
					        "vol": "1.25000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TUI2JG-VOE36-SW7UJQ": {
					        "ordertxid": "OZABVF-MIK6V-L3ZTOE",
					        "postxid": "TF5GVO-T7ZZ2-6NBKBI",
					        "pair": "XXBTZUSD",
					        "time": 1616511385.1402,
					        "type": "sell",
					        "ordertype": "limit",
					        "price": "30000.00000",
					        "cost": "60.00000",
					        "fee": "0.06000",
					        "vol": "0.00200000",
					        "margin": "12.00000",
					        "misc": "closing"
					      },
					      "TXSFI3-5CTX5-LPTJHK": {
					        "ordertxid": "OICKON-6IMOR-2ODO5A",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616511195.1372,
					        "type": "sell",
					        "ordertype": "limit",
					        "price": "30000.00000",
					        "cost": "60.00000",
					        "fee": "0.06000",
					        "vol": "0.00200000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TDRIF6-7SGNJ-IAXFCN": {
					        "ordertxid": "OL5EF7-LFZFJ-QBFM2A",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616492377.7001,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30012.00000",
					        "cost": "37515.00000",
					        "fee": "37.51383",
					        "vol": "1.25000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "T2PT3E-H6OGF-F5UABV": {
					        "ordertxid": "ONVNZL-MHDOA-CES7XV",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616008928.0982,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30008.50000",
					        "cost": "15004.25000",
					        "fee": "0.00000",
					        "vol": "0.50000000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TJPO67-7TNED-Y2BRWM": {
					        "ordertxid": "ONVNZL-MHDOA-CES7XV",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616006978.5925,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30008.00000",
					        "cost": "68058.14400",
					        "fee": "68.06066",
					        "vol": "2.26800000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TYYGJC-KEK4Q-L42ZQ4": {
					        "ordertxid": "OG45VQ-ZYQT7-TGLEZR",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZUSD",
					        "time": 1616005993.5273,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "30006.53702",
					        "cost": "135164.44600",
					        "fee": "135.16765",
					        "vol": "4.50450000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TKK7S7-ZMT6O-AGOP6D": {
					        "ordertxid": "OKZ4TV-6ZJRL-6X2WTO",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2458,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.17229",
					        "fee": "0.00000",
					        "vol": "0.00017212",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TP5XQL-ZN5PG-L6VSNV": {
					        "ordertxid": "OAU5VR-4IAXN-SJW5YB",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2436,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TTIWU5-NQ4VO-JC5BQN": {
					        "ordertxid": "OJ3BNG-YDA52-MDMETR",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2412,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TMBTRV-FTDNZ-P3HUV5": {
					        "ordertxid": "O5X65H-KXHCL-QCLYCT",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2388,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TY44ZN-AIYOJ-SDR7GK": {
					        "ordertxid": "OEKLOP-RDAOE-FZWGKN",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2367,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "T5NRGS-AKQZV-ZHG4E4": {
					        "ordertxid": "OICCXO-CRHEQ-LT67QB",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.234,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "T7CBB7-AQ5O7-P5GQSK": {
					        "ordertxid": "O7ABWG-VU27X-YP2XDI",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2312,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TENBB5-7BZEQ-TNBE66": {
					        "ordertxid": "O4AIPT-GX3QH-HZGYCH",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2283,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TVLXY3-C6N3I-T25754": {
					        "ordertxid": "OW4Z6S-DSUGW-DFTKBT",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.226,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TJH4MR-DOYCR-5555SK": {
					        "ordertxid": "OGHH7F-C74EX-7GAM4K",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2234,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TXQ36N-GN7IZ-5PKY7L": {
					        "ordertxid": "OPVBIW-AJXET-VNXDJ7",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.221,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "T6LBB6-IFMBI-4YPXBX": {
					        "ordertxid": "OKOPXR-U4D6F-GBS4KJ",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2183,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "T4M6VU-EECSP-MELY5T": {
					        "ordertxid": "OJI43X-GINSU-QWE6DK",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2157,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TQA5B5-EZLVE-KTM4M5": {
					        "ordertxid": "O4AARU-PTKL7-HSPOEL",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2128,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TKSIRP-ZUYPW-62FLMV": {
					        "ordertxid": "O6MGUZ-XVWWN-GJC3A2",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2104,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TUEHX7-FFLNW-JMFAR7": {
					        "ordertxid": "OY2YWA-4BNZ2-2VMP4Z",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2079,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TJKYBR-A5AYL-24MXFU": {
					        "ordertxid": "OZ45GG-Y7SGJ-NR4B5A",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2052,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TRKPR4-GWUOY-CHPNRN": {
					        "ordertxid": "OL2F4Z-4WGHJ-HMMCJ2",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614110456.2021,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.11299",
					        "fee": "0.00000",
					        "vol": "0.00011288",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "T37GPE-X26FG-5ZSIIB": {
					        "ordertxid": "OL2F4Z-4WGHJ-HMMCJ2",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3409,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.08721",
					        "fee": "0.00000",
					        "vol": "0.00008712",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TQM3FZ-THK6L-XSHSBX": {
					        "ordertxid": "OVKIE5-LM7NM-NWTF64",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3379,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TVDJWV-BADZ2-CV2THT": {
					        "ordertxid": "O4UMTF-7XT6W-4TEAJ5",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3348,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "T3TO6H-GZ3UQ-SFA567": {
					        "ordertxid": "OAUJQY-M2LW7-URI6P4",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3319,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TQVOXK-5MOHP-SI5VAV": {
					        "ordertxid": "OXWORC-FSWRK-VCYRD5",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3292,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "T4H46E-SDKQ2-MN47XT": {
					        "ordertxid": "OWWORE-77UTK-24JC4S",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3268,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TQMMMA-2OWI3-REGYSF": {
					        "ordertxid": "ORVBV5-WMOB2-XIS2O7",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3242,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TJXDW4-C2AL5-MVS4S7": {
					        "ordertxid": "OJQVC6-2DL7T-N2VG6R",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3215,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TW7OM6-3AZPP-KHOCDH": {
					        "ordertxid": "OIY22N-PA6L3-VZRYWY",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3189,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TVLE6Y-7THFG-DEWRL2": {
					        "ordertxid": "OEOV45-VCSQF-6JC62L",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3165,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TTEUX3-HDAAA-RC2RUO": {
					        "ordertxid": "OH76VO-UKWAD-PSBDX6",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3138,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TK5SLP-HK2DZ-HJAQ3P": {
					        "ordertxid": "OVL7YT-LDN4Z-R4O4QD",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XXBTZEUR",
					        "time": 1614082549.3108,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TB27WI-UMAET-QOYYZL": {
					        "ordertxid": "ORSERE-GNHBA-UDAJNL",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "LTCBTC",
					        "time": 1614082549.3079,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TEFPZR-SAZHO-HCT2LJ": {
					        "ordertxid": "OIMMOC-QVPMC-UA35DQ",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "LTCBTC",
					        "time": 1614082549.3051,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TS6BLY-CYHBC-35B2WE": {
					        "ordertxid": "O2MDN6-6Y6IB-TSVEHO",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "LTCUSD",
					        "time": 1614082549.3015,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      },
					      "TUBIJ2-LJD26-EW3WKN": {
					        "ordertxid": "OFJVXE-B6PHS-HPNIXU",
					        "postxid": "TKH2SE-M7IF5-CFI7LT",
					        "pair": "XBTUSD",
					        "time": 1614082549.2986,
					        "type": "buy",
					        "ordertype": "limit",
					        "price": "1001.00000",
					        "cost": "0.20020",
					        "fee": "0.00000",
					        "vol": "0.00020000",
					        "margin": "0.00000",
					        "misc": ""
					      }
					    },
					    "count": 2346
					  }
					}""";
	private static final String PLACE_ORDER_RESPONSE_BODY =
			"""
					{
					  "error": [],
					  "result": {
					    "descr": {
					      "order": "buy 2.12340000 XBTUSD @ limit 45000.1 with 2:1 leverage",
					      "close": "close position @ stop loss 38000.0 -> limit 36000.0"
					    },
					    "txid": [
					      "OUF4EM-FRGI2-MQMWZD"
					    ]
					  }
					}""";
	private KrakenTradeService classUnderTest;

	@Before
	public void setup() {
		classUnderTest = (KrakenTradeService) createExchange().getTradeService();
	}

	@Test
	public void ordersTest() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonRoot = mapper.readTree(OPEN_ORDERS_BODY);
		stubFor(
				post(urlPathEqualTo("/0/private/OpenOrders"))
						.willReturn(
								aResponse()
										.withStatus(200)
										.withHeader("Content-Type", "application/json")
										.withBody(OPEN_ORDERS_BODY)));
		OpenOrders openOrders = classUnderTest.getOpenOrders();
		assertThat(openOrders).isNotNull();
		assertThat(openOrders.getOpenOrders()).hasSize(jsonRoot.get("result").get("open").size());
		LimitOrder firstOrder = openOrders.getOpenOrders().get(0);
		assertThat(firstOrder).isNotNull();
		assertThat(firstOrder.getOriginalAmount()).isNotNull().isPositive();
		assertThat(firstOrder.getId()).isNotBlank();
		assertThat(firstOrder.getInstrument()).isEqualTo(BTC_USD);
		LimitOrder secondOrder = openOrders.getOpenOrders().get(1);
		assertThat(secondOrder).isNotNull();
		assertThat(secondOrder.getOriginalAmount()).isNotNull().isPositive();
		assertThat(secondOrder.getId()).isNotBlank();
		assertThat(secondOrder.getInstrument()).isEqualTo(LTC_USD);
	}

	@Test
	public void openOrdersByCurrencyPairTest() throws Exception {
		stubFor(
				post(urlPathEqualTo("/0/private/OpenOrders"))
						.willReturn(
								aResponse()
										.withStatus(200)
										.withHeader("Content-Type", "application/json")
										.withBody(OPEN_ORDERS_BODY)));
		DefaultOpenOrdersParamCurrencyPair defaultOpenOrdersParamCurrencyPair =
				new DefaultOpenOrdersParamCurrencyPair(BTC_USD);
		OpenOrders openOrders = classUnderTest.getOpenOrders(defaultOpenOrdersParamCurrencyPair);
		assertThat(openOrders).isNotNull();
		assertThat(openOrders.getOpenOrders().size()).isEqualTo(3);
		LimitOrder firstOrder = openOrders.getOpenOrders().get(0);
		assertThat(firstOrder).isNotNull();
		assertThat(firstOrder.getOriginalAmount()).isNotNull().isPositive();
		assertThat(firstOrder.getId()).isNotBlank();
		assertThat(firstOrder.getInstrument()).isEqualTo(BTC_USD);
		LimitOrder secondOrder = openOrders.getOpenOrders().get(1);
		assertThat(secondOrder).isNotNull();
		assertThat(secondOrder.getOriginalAmount()).isNotNull().isPositive();
		assertThat(secondOrder.getId()).isNotBlank();
		assertThat(secondOrder.getInstrument()).isEqualTo(BTC_USD);
	}

	@Test
	public void tradeHistoryTest() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonRoot = mapper.readTree(ORDER_HISTORY_BODY);
		stubFor(
				post(urlPathEqualTo("/0/private/TradesHistory"))
						.willReturn(
								aResponse()
										.withStatus(200)
										.withHeader("Content-Type", "application/json")
										.withBody(ORDER_HISTORY_BODY)));
		TradeHistoryParams tradeHistoryParams = classUnderTest.createTradeHistoryParams();
		UserTrades userTrades = classUnderTest.getTradeHistory(tradeHistoryParams);
		assertThat(userTrades).isNotNull();
		assertThat(userTrades.getUserTrades()).hasSize(jsonRoot.get("result").get("trades").size());
		UserTrade firstUserTrade = userTrades.getUserTrades().get(0);
		assertThat(firstUserTrade).isNotNull();
		assertThat(firstUserTrade.getOriginalAmount()).isNotNull().isPositive();
		assertThat(firstUserTrade.getId()).isNotBlank();
		assertThat(firstUserTrade.getInstrument()).isEqualTo(BTC_USD);
		UserTrade secondUserTrade = userTrades.getUserTrades().get(1);
		assertThat(secondUserTrade).isNotNull();
		assertThat(secondUserTrade.getOriginalAmount()).isNotNull().isPositive();
		assertThat(secondUserTrade.getId()).isNotBlank();
		assertThat(secondUserTrade.getInstrument()).isEqualTo(LTC_USD);
	}

	@Test
	public void placeOrderTest() throws Exception {
		stubAddOrderApi();
		String orderId = classUnderTest.placeLimitOrder(LIMIT_ORDER);
		assertThat(orderId).isEqualTo("OUF4EM-FRGI2-MQMWZD");
		List<LoggedRequest> requests =
				wireMockRule.findAll(postRequestedFor(urlEqualTo("/0/private/AddOrder")));
		assertThat(requests).hasSize(1);
		Map<String, String> requestParams = parseAddOrderRequestBody(requests.get(0));
		assertThat(requestParams.get("type")).isEqualTo("buy");
		assertThat(requestParams.get("volume")).isEqualTo("2.12340000");
		assertThat(requestParams.get("pair")).isEqualTo("XXBTZUSD");
		assertThat(requestParams.get("price")).isEqualTo("45000.1");
	}

	private void stubAddOrderApi() {
		stubFor(
				post(urlPathEqualTo("/0/private/AddOrder"))
						.willReturn(
								aResponse()
										.withStatus(200)
										.withHeader("Content-Type", "application/json")
										.withBody(PLACE_ORDER_RESPONSE_BODY)));
	}

	private Map<String, String> parseAddOrderRequestBody(LoggedRequest request) {
		return Arrays.stream(request.getBodyAsString().split("&"))
				.map(keyValueString -> keyValueString.split("=", 2))
				.collect(Collectors.toMap(a -> a[0], b -> b[1]));
	}

	@Test
	public void placeOrderWithTimeInForceTest() throws Exception {
		stubAddOrderApi();
		LimitOrder order = LIMIT_ORDER;
		order.addOrderFlag(TimeInForce.IOC);
		classUnderTest.placeLimitOrder(order);
		List<LoggedRequest> requests =
				wireMockRule.findAll(postRequestedFor(urlEqualTo("/0/private/AddOrder")));
		assertThat(requests).hasSize(1);
		Map<String, String> requestParams = parseAddOrderRequestBody(requests.get(0));
		assertThat(requestParams.get("timeinforce")).isEqualTo("IOC");
	}
}