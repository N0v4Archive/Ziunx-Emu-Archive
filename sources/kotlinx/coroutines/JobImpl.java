package kotlinx.coroutines;

/* loaded from: classes.dex */
public class JobImpl extends JobSupport implements CompletableJob {
    private final boolean handlesException;

    public JobImpl(Job job) {
        super(true);
        initParentJob(job);
        this.handlesException = handlesException();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
    
        r3 = r3.getParentHandle$kotlinx_coroutines_core();
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
    
        if ((r3 instanceof kotlinx.coroutines.ChildHandleNode) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
    
        r3 = (kotlinx.coroutines.ChildHandleNode) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        if (r3 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
    
        r3 = r3.getJob();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        if (r3 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002a, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x001d, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0014, code lost:
    
        if (r3 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
    
        if (r3.getHandlesException$kotlinx_coroutines_core() == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean handlesException() {
        /*
            r3 = this;
            kotlinx.coroutines.ChildHandle r3 = r3.getParentHandle$kotlinx_coroutines_core()
            boolean r0 = r3 instanceof kotlinx.coroutines.ChildHandleNode
            r1 = 0
            if (r0 == 0) goto Lc
            kotlinx.coroutines.ChildHandleNode r3 = (kotlinx.coroutines.ChildHandleNode) r3
            goto Ld
        Lc:
            r3 = r1
        Ld:
            r0 = 0
            if (r3 == 0) goto L33
            kotlinx.coroutines.JobSupport r3 = r3.getJob()
            if (r3 != 0) goto L17
            goto L33
        L17:
            boolean r2 = r3.getHandlesException$kotlinx_coroutines_core()
            if (r2 == 0) goto L1f
            r3 = 1
            return r3
        L1f:
            kotlinx.coroutines.ChildHandle r3 = r3.getParentHandle$kotlinx_coroutines_core()
            boolean r2 = r3 instanceof kotlinx.coroutines.ChildHandleNode
            if (r2 == 0) goto L2a
            kotlinx.coroutines.ChildHandleNode r3 = (kotlinx.coroutines.ChildHandleNode) r3
            goto L2b
        L2a:
            r3 = r1
        L2b:
            if (r3 == 0) goto L33
            kotlinx.coroutines.JobSupport r3 = r3.getJob()
            if (r3 != 0) goto L17
        L33:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobImpl.handlesException():boolean");
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean getHandlesException$kotlinx_coroutines_core() {
        return this.handlesException;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }
}
