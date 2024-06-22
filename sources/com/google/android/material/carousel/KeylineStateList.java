package com.google.android.material.carousel;

import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class KeylineStateList {
    private final KeylineState defaultState;
    private final float leftShiftRange;
    private final List leftStateSteps;
    private final float[] leftStateStepsInterpolationPoints;
    private final float rightShiftRange;
    private final List rightStateSteps;
    private final float[] rightStateStepsInterpolationPoints;

    private KeylineStateList(KeylineState keylineState, List list, List list2) {
        this.defaultState = keylineState;
        this.leftStateSteps = Collections.unmodifiableList(list);
        this.rightStateSteps = Collections.unmodifiableList(list2);
        float f = ((KeylineState) list.get(list.size() - 1)).getFirstKeyline().loc - keylineState.getFirstKeyline().loc;
        this.leftShiftRange = f;
        float f2 = keylineState.getLastKeyline().loc - ((KeylineState) list2.get(list2.size() - 1)).getLastKeyline().loc;
        this.rightShiftRange = f2;
        this.leftStateStepsInterpolationPoints = getStateStepInterpolationPoints(f, list, true);
        this.rightStateStepsInterpolationPoints = getStateStepInterpolationPoints(f2, list2, false);
    }

    private static int findFirstInBoundsKeylineIndex(KeylineState keylineState) {
        for (int i = 0; i < keylineState.getKeylines().size(); i++) {
            if (((KeylineState.Keyline) keylineState.getKeylines().get(i)).locOffset >= 0.0f) {
                return i;
            }
        }
        return -1;
    }

    private static int findFirstIndexAfterLastFocalKeylineWithMask(KeylineState keylineState, float f) {
        for (int lastFocalKeylineIndex = keylineState.getLastFocalKeylineIndex(); lastFocalKeylineIndex < keylineState.getKeylines().size(); lastFocalKeylineIndex++) {
            if (f == ((KeylineState.Keyline) keylineState.getKeylines().get(lastFocalKeylineIndex)).mask) {
                return lastFocalKeylineIndex;
            }
        }
        return keylineState.getKeylines().size() - 1;
    }

    private static int findLastInBoundsKeylineIndex(Carousel carousel, KeylineState keylineState) {
        for (int size = keylineState.getKeylines().size() - 1; size >= 0; size--) {
            if (((KeylineState.Keyline) keylineState.getKeylines().get(size)).locOffset <= carousel.getContainerWidth()) {
                return size;
            }
        }
        return -1;
    }

    private static int findLastIndexBeforeFirstFocalKeylineWithMask(KeylineState keylineState, float f) {
        for (int firstFocalKeylineIndex = keylineState.getFirstFocalKeylineIndex() - 1; firstFocalKeylineIndex >= 0; firstFocalKeylineIndex--) {
            if (f == ((KeylineState.Keyline) keylineState.getKeylines().get(firstFocalKeylineIndex)).mask) {
                return firstFocalKeylineIndex;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeylineStateList from(Carousel carousel, KeylineState keylineState) {
        return new KeylineStateList(keylineState, getStateStepsLeft(keylineState), getStateStepsRight(carousel, keylineState));
    }

    private static float[] getStateStepInterpolationPoints(float f, List list, boolean z) {
        int size = list.size();
        float[] fArr = new float[size];
        int i = 1;
        while (i < size) {
            int i2 = i - 1;
            KeylineState keylineState = (KeylineState) list.get(i2);
            KeylineState keylineState2 = (KeylineState) list.get(i);
            fArr[i] = i == size + (-1) ? 1.0f : fArr[i2] + ((z ? keylineState2.getFirstKeyline().loc - keylineState.getFirstKeyline().loc : keylineState.getLastKeyline().loc - keylineState2.getLastKeyline().loc) / f);
            i++;
        }
        return fArr;
    }

    private static List getStateStepsLeft(KeylineState keylineState) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState);
        int findFirstInBoundsKeylineIndex = findFirstInBoundsKeylineIndex(keylineState);
        if (!isFirstFocalItemAtLeftOfContainer(keylineState) && findFirstInBoundsKeylineIndex != -1) {
            int firstFocalKeylineIndex = (keylineState.getFirstFocalKeylineIndex() - 1) - findFirstInBoundsKeylineIndex;
            float f = keylineState.getFirstKeyline().locOffset - (keylineState.getFirstKeyline().maskedItemSize / 2.0f);
            for (int i = 0; i <= firstFocalKeylineIndex; i++) {
                KeylineState keylineState2 = (KeylineState) arrayList.get(arrayList.size() - 1);
                int size = keylineState.getKeylines().size() - 1;
                int i2 = (findFirstInBoundsKeylineIndex + i) - 1;
                if (i2 >= 0) {
                    size = findFirstIndexAfterLastFocalKeylineWithMask(keylineState2, ((KeylineState.Keyline) keylineState.getKeylines().get(i2)).mask) - 1;
                }
                arrayList.add(moveKeylineAndCreateKeylineState(keylineState2, findFirstInBoundsKeylineIndex, size, f, (keylineState.getFirstFocalKeylineIndex() - i) - 1, (keylineState.getLastFocalKeylineIndex() - i) - 1));
            }
        }
        return arrayList;
    }

    private static List getStateStepsRight(Carousel carousel, KeylineState keylineState) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState);
        int findLastInBoundsKeylineIndex = findLastInBoundsKeylineIndex(carousel, keylineState);
        if (!isLastFocalItemAtRightOfContainer(carousel, keylineState) && findLastInBoundsKeylineIndex != -1) {
            int lastFocalKeylineIndex = findLastInBoundsKeylineIndex - keylineState.getLastFocalKeylineIndex();
            float f = keylineState.getFirstKeyline().locOffset - (keylineState.getFirstKeyline().maskedItemSize / 2.0f);
            for (int i = 0; i < lastFocalKeylineIndex; i++) {
                KeylineState keylineState2 = (KeylineState) arrayList.get(arrayList.size() - 1);
                int i2 = (findLastInBoundsKeylineIndex - i) + 1;
                arrayList.add(moveKeylineAndCreateKeylineState(keylineState2, findLastInBoundsKeylineIndex, i2 < keylineState.getKeylines().size() ? findLastIndexBeforeFirstFocalKeylineWithMask(keylineState2, ((KeylineState.Keyline) keylineState.getKeylines().get(i2)).mask) + 1 : 0, f, keylineState.getFirstFocalKeylineIndex() + i + 1, keylineState.getLastFocalKeylineIndex() + i + 1));
            }
        }
        return arrayList;
    }

    private static boolean isFirstFocalItemAtLeftOfContainer(KeylineState keylineState) {
        return keylineState.getFirstFocalKeyline().locOffset - (keylineState.getFirstFocalKeyline().maskedItemSize / 2.0f) <= 0.0f || keylineState.getFirstFocalKeyline() == keylineState.getFirstKeyline();
    }

    private static boolean isLastFocalItemAtRightOfContainer(Carousel carousel, KeylineState keylineState) {
        return keylineState.getLastFocalKeyline().locOffset + (keylineState.getLastFocalKeyline().maskedItemSize / 2.0f) >= ((float) carousel.getContainerWidth()) || keylineState.getLastFocalKeyline() == keylineState.getLastKeyline();
    }

    private static KeylineState lerp(List list, float f, float[] fArr) {
        int size = list.size();
        float f2 = fArr[0];
        int i = 1;
        while (i < size) {
            float f3 = fArr[i];
            if (f <= f3) {
                return KeylineState.lerp((KeylineState) list.get(i - 1), (KeylineState) list.get(i), AnimationUtils.lerp(0.0f, 1.0f, f2, f3, f));
            }
            i++;
            f2 = f3;
        }
        return (KeylineState) list.get(0);
    }

    private static KeylineState moveKeylineAndCreateKeylineState(KeylineState keylineState, int i, int i2, float f, int i3, int i4) {
        ArrayList arrayList = new ArrayList(keylineState.getKeylines());
        arrayList.add(i2, (KeylineState.Keyline) arrayList.remove(i));
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.getItemSize());
        int i5 = 0;
        while (i5 < arrayList.size()) {
            KeylineState.Keyline keyline = (KeylineState.Keyline) arrayList.get(i5);
            float f2 = keyline.maskedItemSize;
            builder.addKeyline((f2 / 2.0f) + f, keyline.mask, f2, i5 >= i3 && i5 <= i4);
            f += keyline.maskedItemSize;
            i5++;
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeylineState getDefaultState() {
        return this.defaultState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeylineState getLeftState() {
        return (KeylineState) this.leftStateSteps.get(r1.size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeylineState getRightState() {
        return (KeylineState) this.rightStateSteps.get(r1.size() - 1);
    }

    public KeylineState getShiftedState(float f, float f2, float f3) {
        float f4 = this.leftShiftRange + f2;
        float f5 = f3 - this.rightShiftRange;
        if (f < f4) {
            return lerp(this.leftStateSteps, AnimationUtils.lerp(1.0f, 0.0f, f2, f4, f), this.leftStateStepsInterpolationPoints);
        }
        if (f <= f5) {
            return this.defaultState;
        }
        return lerp(this.rightStateSteps, AnimationUtils.lerp(0.0f, 1.0f, f5, f3, f), this.rightStateStepsInterpolationPoints);
    }
}
