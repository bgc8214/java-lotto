import domain.LottoPrizeType;
import domain.WinningStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
public class WinningStatisticsTest {

    @Test
    public void 생성테스트() {
        WinningStatistics winningStatistics = new WinningStatistics();
        for (LottoPrizeType lottoPrizeType : LottoPrizeType.values()) {
            assertThat(winningStatistics.getWinningStatistic().containsKey(lottoPrizeType)).isEqualTo(true);
        }
    }

    @Test
    public void 카운트증가_테스트() {
        WinningStatistics winningStatistics = new WinningStatistics();
        Optional<LottoPrizeType> lottoPrizeTypeOptional = Optional.of(LottoPrizeType.FIRST_PRIZE);
        assertThat(winningStatistics.addCount(lottoPrizeTypeOptional)).isEqualTo(1);
    }

    @DisplayName("5000원 1개 당첨시 수익률 계산 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1000,5", "5000,1", "10000,0.5"})
    public void 수익률계산_테스트(int purchaseAmount, double lottoYield) {
        WinningStatistics winningStatistics = new WinningStatistics();
        Optional<LottoPrizeType> lottoPrizeTypeOptional = Optional.of(LottoPrizeType.FOURTH_PRIZE);
        winningStatistics.addCount(lottoPrizeTypeOptional);
        assertThat(winningStatistics.calcLottoYield(purchaseAmount)).isEqualTo(lottoYield);
    }
}
