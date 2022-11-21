package bridge;

import static bridge.InputView.*;
import static bridge.OutputView.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final List<String> BRIDGE_ANSWER;
    private List<String> playerList = new ArrayList<>();
    private Boolean success = false;
    private Integer tryCount = 0;
    private Integer count = 0;

    public BridgeGame(List<String> BRIDGE_ANSWER) {
        this.BRIDGE_ANSWER = BRIDGE_ANSWER;

        Boolean keepPlay = true;
        while (keepPlay) {
            keepPlay = Play();
        }
        printResult(success, tryCount);
        printMap(playerList, BRIDGE_ANSWER);
    }

    private Boolean Play() {
        count = 0;
        Boolean repeat = true;

        while (repeat) {
            repeat = Proceed();
        }
        tryCount ++;
        return retry();
    }

    private Boolean Proceed() {
        try {
            System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
            String playerInput = InputView.readMoving();
            if (BRIDGE_ANSWER.get(count).equals(playerList.get(count))) {
                move(playerInput);
                count ++;
                return true;
            }
            if (!BRIDGE_ANSWER.get(count).equals(playerList.get(count))) {
                move(playerInput);
                return false;
            }
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String playerInput) {
        playerList.add(playerInput);
        System.out.println(playerInput);
        printMap(playerList, BRIDGE_ANSWER);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public Boolean retry() {
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        return readGameCommand();
    }
}
