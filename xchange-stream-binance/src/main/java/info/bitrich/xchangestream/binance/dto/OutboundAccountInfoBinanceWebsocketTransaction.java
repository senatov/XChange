package info.bitrich.xchangestream.binance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.dto.account.Balance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OutboundAccountInfoBinanceWebsocketTransaction
		extends BaseBinanceWebSocketTransaction {

	private final BigDecimal makerCommissionRate;
	private final BigDecimal takerCommissionRate;
	private final BigDecimal buyerCommissionRate;
	private final BigDecimal sellerCommissionRate;
	private final boolean canTrade;
	private final boolean canWithdraw;
	private final boolean canDeposit;
	private final long lastUpdateTimestamp;
	private final List<BinanceWebsocketBalance> balances;
	private final List<String> permissions;

	public OutboundAccountInfoBinanceWebsocketTransaction(
			@JsonProperty("e") String eventType,
			@JsonProperty("E") String eventTime,
			@JsonProperty("m") BigDecimal makerCommissionRate,
			@JsonProperty("t") BigDecimal takerCommissionRate,
			@JsonProperty("b") BigDecimal buyerCommissionRate,
			@JsonProperty("s") BigDecimal sellerCommissionRate,
			@JsonProperty("T") boolean canTrade,
			@JsonProperty("W") boolean canWithdraw,
			@JsonProperty("D") boolean canDeposit,
			@JsonProperty("u") long lastUpdateTimestamp,
			@JsonProperty("B") List<BinanceWebsocketBalance> balances,
			@JsonProperty("P") List<String> permissions) {
		super(eventType, eventTime);
		this.makerCommissionRate = makerCommissionRate;
		this.takerCommissionRate = takerCommissionRate;
		this.buyerCommissionRate = buyerCommissionRate;
		this.sellerCommissionRate = sellerCommissionRate;
		this.canTrade = canTrade;
		this.canWithdraw = canWithdraw;
		this.canDeposit = canDeposit;
		this.lastUpdateTimestamp = lastUpdateTimestamp;
		this.balances = balances;
		this.permissions = permissions;
	}

	public BigDecimal getMakerCommissionRate() {
		return makerCommissionRate;
	}

	public BigDecimal getTakerCommissionRate() {
		return takerCommissionRate;
	}

	public BigDecimal getBuyerCommissionRate() {
		return buyerCommissionRate;
	}

	public BigDecimal getSellerCommissionRate() {
		return sellerCommissionRate;
	}

	public boolean isCanTrade() {
		return canTrade;
	}

	public boolean isCanWithdraw() {
		return canWithdraw;
	}

	public boolean isCanDeposit() {
		return canDeposit;
	}

	public long getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public List<BinanceWebsocketBalance> getBalances() {
		return balances;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public List<Balance> toBalanceList() {
		return balances.stream()
				.map(
						b ->
								new Balance(
										b.getCurrency(),
										b.getTotal(),
										b.getAvailable(),
										b.getLocked(),
										BigDecimal.ZERO,
										BigDecimal.ZERO,
										BigDecimal.ZERO,
										BigDecimal.ZERO,
										new Date(lastUpdateTimestamp)))
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "OutboundAccountInfoBinanceWebsocketTransaction [makerCommissionRate="
				+ makerCommissionRate
				+ ", takerCommissionRate="
				+ takerCommissionRate
				+ ", buyerCommissionRate="
				+ buyerCommissionRate
				+ ", sellerCommissionRate="
				+ sellerCommissionRate
				+ ", canTrade="
				+ canTrade
				+ ", canWithdraw="
				+ canWithdraw
				+ ", canDeposit="
				+ canDeposit
				+ ", lastUpdateTimestamp="
				+ lastUpdateTimestamp
				+ ", balances="
				+ balances
				+ ", permissions="
				+ permissions
				+ "]";
	}
}