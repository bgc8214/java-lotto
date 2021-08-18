package controller;

import domain.*;
import view.InputView;
import view.ResultView;

public class LottoController {

    public static void startLotto() {
        int purchaseAmount = InputView.enterPurchaseAmount();
        LottoManager lottoManager = new LottoManager(purchaseAmount);
        ResultView.purchaseResult(lottoManager.getPurchaseLottoCount(purchaseAmount));
        ResultView.lottoNumberResult(lottoManager.getLottos().getValue());

        System.err.println("지난 주 당첨 번호를 입력해 주세요.");
        Lotto lotto = lottoManager.makeLotto(new ManualNumberStrategy());
        System.err.println("보너스 볼을 입력해 주세요.");
        BonusBall bonusBall = lottoManager.makeBonusBall(InputView.enterNumber());
        WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);

        lottoManager.findWinningLottoResult(winningLotto);
        ResultView.winningResult(lottoManager.getWinningResult());
        ResultView.yieldResult(lottoManager.getLottoYield(purchaseAmount));
    }
}
