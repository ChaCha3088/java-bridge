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
        System.out.println(BRIDGE_ANSWER);

        Boolean keepPlay = true;
        Boolean retry = true;
        while (retry) {
            while (keepPlay) {
                keepPlay = Play();
            }
            if (count.equals(BRIDGE_ANSWER.size())) {
                break;
            }
            retry = retry();
            keepPlay = retry;
        }

        printResult(success, tryCount);
        printMap(playerList, BRIDGE_ANSWER);
    }

    private Boolean Play() {
        count = 0;
        playerList.clear();
        Boolean repeat = true;

        while (repeat && (count < BRIDGE_ANSWER.size())) {
            repeat = Proceed();
        }
        if (count.equals(BRIDGE_ANSWER.size())) {
            success = true;
        }
        tryCount ++;
        return false;
    }

    private Boolean Proceed() {
        String playerInput = "";
        while (true) {
            try {
                playerInput = InputView.readMoving();
                break;
            } catch (IllegalArgumentException e) {
                OutputView.String("[ERROR] 유효한 값이 아닙니다.");
            }
        }
        if (BRIDGE_ANSWER.get(count).equals(playerInput)) {
            move(playerInput);
            count ++;
            return true;
        }
        if (!BRIDGE_ANSWER.get(count).equals(playerInput)) {
            move(playerInput);
            count ++;
            return false;
        }
        return false;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String playerInput) {
        playerList.add(playerInput);
        String(playerInput);
        printMap(playerList, BRIDGE_ANSWER);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public Boolean retry() {
        Boolean result = null;
        while (true) {
            try {
                result = readGameCommand();
                break;
            } catch (IllegalArgumentException e) {
                OutputView.String("[ERROR] 유효한 값이 아닙니다.");
            }
        }
        return result;
    }
}
