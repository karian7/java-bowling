package bowling.domain.bowlresult;

import bowling.domain.Score;
import bowling.domain.Trial;
import bowling.domain.framestate.FrameState;
import bowling.exception.CannotBowlException;

public abstract class BowlResult {
  private static final int MAX_TRIAL_COUNT = 2;

  Trial first;
  Trial second;

  public Trial getFirst() {
    return first;
  }

  public Trial getSecond() {
    return second;
  }

  public abstract FrameState getState();

  public int getRolledBowlCount() {
    int bowlCount = 0;
    if (first.isPlayed()) {
      bowlCount++;
    }

    if (second.isPlayed()) {
      bowlCount++;
    }

    return bowlCount;
  }

  public abstract void roll(int pinCount) throws CannotBowlException;

  public Score getScore(int trialCount) {
    if (trialCount == 0) {
      return Score.zero();
    }

    if (trialCount == 1) {
      return Score.of(first);
    }
    if (trialCount == 2) {
      return Score.add(Score.of(first), Score.of(second));
    }

    return Score.ofNull();
  }


  public Score getScoreAll() {
      return getScore(MAX_TRIAL_COUNT);
  }

  public boolean isFinished() {
    return !first.isNotPlayed() && !second.isNotPlayed();
  }
}