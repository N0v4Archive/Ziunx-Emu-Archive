package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class Chain {
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0031, code lost:
    
        if (r7 == 2) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0046, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x0044, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x0042, code lost:
    
        if (r7 == 2) goto L25;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x025f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x04d6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x04e2  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0512 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:168:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02b8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x039a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0351 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03a3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x04c3  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01b3  */
    /* JADX WARN: Type inference failed for: r1v40, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r8v41 */
    /* JADX WARN: Type inference failed for: r8v42 */
    /* JADX WARN: Type inference failed for: r8v47 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r37, androidx.constraintlayout.core.LinearSystem r38, int r39, int r40, androidx.constraintlayout.core.widgets.ChainHead r41) {
        /*
            Method dump skipped, instructions count: 1331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Chain.applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.LinearSystem, int, int, androidx.constraintlayout.core.widgets.ChainHead):void");
    }

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList arrayList, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            i2 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = 0;
        } else {
            i2 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i3 = 2;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            ChainHead chainHead = chainHeadArr[i4];
            chainHead.define();
            if (arrayList == null || arrayList.contains(chainHead.mFirst)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            }
        }
    }
}
