package org.knowm.xchange.kraken.dto.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.kraken.dto.account.results.KrakenBalanceResult;
import org.knowm.xchange.kraken.dto.account.results.KrakenLedgerResult;
import org.knowm.xchange.kraken.dto.account.results.KrakenTradeBalanceInfoResult;
import org.knowm.xchange.kraken.dto.account.results.KrakenTradeVolumeResult;
import org.knowm.xchange.kraken.dto.account.results.KrakenWebsocketTokenResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class KrakenAccountJSONTest {

	@Before
	public void setUp() {
	}

	@Test
	public void testBalanceUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenAccountJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/account/example-balance-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenBalanceResult krakenBalance = mapper.readValue(is, KrakenBalanceResult.class);
		Assert.assertEquals(3, krakenBalance.getResult().size());
		assertThat(krakenBalance.getResult().get("ZUSD")).isNull();
		assertThat(krakenBalance.getResult().get("ZEUR")).isEqualTo("1.0539");
	}

	@Test
	public void testBalanceInfoUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenAccountJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/account/example-tradebalanceinfo-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenTradeBalanceInfoResult krakenResult =
				mapper.readValue(is, KrakenTradeBalanceInfoResult.class);
		KrakenTradeBalanceInfo tradeBalanceInfo = krakenResult.getResult();
		assertThat(tradeBalanceInfo.tradeBalance()).isEqualTo("71.6310");
		assertThat(tradeBalanceInfo.margin()).isEqualTo("0.0000");
		assertThat(tradeBalanceInfo.freeMargin()).isEqualTo("71.6310");
		assertThat(tradeBalanceInfo.costBasis()).isEqualTo("0.0000");
		assertThat(tradeBalanceInfo.equity()).isEqualTo("71.6310");
		assertThat(tradeBalanceInfo.floatingValuation()).isEqualTo("0.0000");
		assertThat(tradeBalanceInfo.unrealizedGainsLosses()).isEqualTo("0.0000");
	}

	@Test
	public void testLedgerInfoUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenAccountJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/account/example-ledgerinfo-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenLedgerResult krakenResult = mapper.readValue(is, KrakenLedgerResult.class);
		Map<String, KrakenLedger> ledgerInfo = krakenResult.getResult().getLedgerMap();
		KrakenLedger deposit = ledgerInfo.get("LQY6IE-WNT47-JRBOJV");
		assertThat(deposit.asset()).isEqualTo("XXBT");
		assertThat(deposit.assetClass()).isEqualTo("currency");
		assertThat(deposit.balance()).isEqualTo("0.1000000000");
		assertThat(deposit.fee()).isEqualTo("0.0000000000");
		assertThat(deposit.transactionAmount()).isEqualTo("0.1000000000");
		assertThat(deposit.ledgerType()).isEqualTo(LedgerType.DEPOSIT);
		assertThat(deposit.refId()).isEqualTo("QGBJIZV-4F6SPK-ZCBT5O");
		assertThat(deposit.unixTime()).isEqualTo(1391400160.0679);
		KrakenLedger settled = ledgerInfo.get("L23XKW-ZZHEP-FJINZ3");
		assertThat(settled.asset()).isEqualTo("XETH");
		assertThat(settled.assetClass()).isEqualTo("currency");
		assertThat(settled.balance()).isEqualTo("0.5000000000");
		assertThat(settled.fee()).isEqualTo("0.0000000000");
		assertThat(settled.transactionAmount()).isEqualTo("0.5000000000");
		assertThat(settled.ledgerType()).isEqualTo(LedgerType.SETTLED);
		assertThat(settled.refId()).isEqualTo("TPYCPK-GLBCV-NGPDDI");
		assertThat(settled.unixTime()).isEqualTo(1388739905.0371);
	}

	@Test
	public void testTradeVolumeUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenAccountJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/account/example-tradevolume-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenTradeVolumeResult krakenResult = mapper.readValue(is, KrakenTradeVolumeResult.class);
		KrakenTradeVolume tradeVolume = krakenResult.getResult();
		assertThat(tradeVolume.getCurrency()).isEqualTo("ZUSD");
		assertThat(tradeVolume.getVolume()).isEqualTo("451.3040");
		Map<String, KrakenVolumeFee> fees = tradeVolume.getFees();
		KrakenVolumeFee fee = fees.get("XXBTZUSD");
		assertThat(fee.fee()).isEqualTo("0.3000");
		assertThat(fee.minFee()).isEqualTo("0.0500");
		assertThat(fee.maxFee()).isEqualTo("0.3000");
		assertThat(fee.nextFee()).isEqualTo("0.2900");
		assertThat(fee.nextVolume()).isEqualTo("1000.0000");
		assertThat(fee.tierVolume()).isEqualTo("0.0000");
		KrakenVolumeFee maker = tradeVolume.getFeesMaker().get("XXBTZUSD");
		assertThat(maker.fee()).isEqualTo("0.1600");
		assertThat(maker.minFee()).isEqualTo("0.0000");
		assertThat(maker.maxFee()).isEqualTo("0.1600");
		assertThat(maker.nextFee()).isEqualTo("0.1400");
		assertThat(maker.nextVolume()).isEqualTo("1000.0000");
		assertThat(maker.tierVolume()).isEqualTo("0.0000");
	}

	@Test
	public void testKrakenWebsocketTokenUnmarshal() throws IOException {
		// Read in the JSON from the example resources
		InputStream is =
				KrakenAccountJSONTest.class.getResourceAsStream(
						"/org/knowm/xchange/kraken/dto/account/example-krakenWebsocketToken-data.json");
		// Use Jackson to parse it
		ObjectMapper mapper = new ObjectMapper();
		KrakenWebsocketTokenResult krakenResult =
				mapper.readValue(is, KrakenWebsocketTokenResult.class);
		KrakenWebsocketToken tokenResult = krakenResult.getResult();
		assertThat(tokenResult.token()).isEqualTo("44444444444444444444444444444444");
		assertThat(tokenResult.expiresInSeconds()).isEqualTo(900);
	}
}