package kotlinx.coroutines.channels;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;

/* loaded from: classes.dex */
public class BufferedChannel implements Channel {
    private volatile Object _closeCause;
    private volatile long bufferEnd;
    private volatile Object bufferEndSegment;
    private final int capacity;
    private volatile Object closeHandler;
    private volatile long completedExpandBuffersAndPauseFlag;
    public final Function1 onUndeliveredElement;
    private final Function3 onUndeliveredElementReceiveCancellationConstructor;
    private volatile Object receiveSegment;
    private volatile long receivers;
    private volatile Object sendSegment;
    private volatile long sendersAndCloseStatus;
    private static final AtomicLongFieldUpdater sendersAndCloseStatus$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "sendersAndCloseStatus");
    private static final AtomicLongFieldUpdater receivers$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "receivers");
    private static final AtomicLongFieldUpdater bufferEnd$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "bufferEnd");
    private static final AtomicLongFieldUpdater completedExpandBuffersAndPauseFlag$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "completedExpandBuffersAndPauseFlag");
    private static final AtomicReferenceFieldUpdater sendSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "sendSegment");
    private static final AtomicReferenceFieldUpdater receiveSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "receiveSegment");
    private static final AtomicReferenceFieldUpdater bufferEndSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "bufferEndSegment");
    private static final AtomicReferenceFieldUpdater _closeCause$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "_closeCause");
    private static final AtomicReferenceFieldUpdater closeHandler$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "closeHandler");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class BufferedChannelIterator implements ChannelIterator, Waiter {
        private CancellableContinuationImpl continuation;
        private Object receiveResult;

        public BufferedChannelIterator() {
            Symbol symbol;
            symbol = BufferedChannelKt.NO_RECEIVE_RESULT;
            this.receiveResult = symbol;
        }

        private final Object hasNextOnNoWaiterSuspend(ChannelSegment channelSegment, int i, long j, Continuation continuation) {
            Continuation intercepted;
            Symbol symbol;
            Symbol symbol2;
            Boolean boxBoolean;
            Symbol symbol3;
            Symbol symbol4;
            Symbol symbol5;
            Object coroutine_suspended;
            BufferedChannel bufferedChannel = BufferedChannel.this;
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
            try {
                this.continuation = orCreateCancellableContinuation;
                Object updateCellReceive = bufferedChannel.updateCellReceive(channelSegment, i, j, this);
                symbol = BufferedChannelKt.SUSPEND;
                if (updateCellReceive == symbol) {
                    bufferedChannel.prepareReceiverForSuspension(this, channelSegment, i);
                } else {
                    symbol2 = BufferedChannelKt.FAILED;
                    Function1 function1 = null;
                    if (updateCellReceive == symbol2) {
                        if (j < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                            channelSegment.cleanPrev();
                        }
                        ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.receiveSegment$FU.get(bufferedChannel);
                        while (true) {
                            if (bufferedChannel.isClosedForReceive()) {
                                onClosedHasNextNoWaiterSuspend();
                                break;
                            }
                            long andIncrement = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel);
                            int i2 = BufferedChannelKt.SEGMENT_SIZE;
                            long j2 = andIncrement / i2;
                            int i3 = (int) (andIncrement % i2);
                            if (channelSegment2.id != j2) {
                                ChannelSegment findSegmentReceive = bufferedChannel.findSegmentReceive(j2, channelSegment2);
                                if (findSegmentReceive != null) {
                                    channelSegment2 = findSegmentReceive;
                                }
                            }
                            Object updateCellReceive2 = bufferedChannel.updateCellReceive(channelSegment2, i3, andIncrement, this);
                            symbol3 = BufferedChannelKt.SUSPEND;
                            if (updateCellReceive2 == symbol3) {
                                bufferedChannel.prepareReceiverForSuspension(this, channelSegment2, i3);
                                break;
                            }
                            symbol4 = BufferedChannelKt.FAILED;
                            if (updateCellReceive2 != symbol4) {
                                symbol5 = BufferedChannelKt.SUSPEND_NO_WAITER;
                                if (updateCellReceive2 == symbol5) {
                                    throw new IllegalStateException("unexpected".toString());
                                }
                                channelSegment2.cleanPrev();
                                this.receiveResult = updateCellReceive2;
                                this.continuation = null;
                                boxBoolean = Boxing.boxBoolean(true);
                                Function1 function12 = bufferedChannel.onUndeliveredElement;
                                if (function12 != null) {
                                    function1 = OnUndeliveredElementKt.bindCancellationFun(function12, updateCellReceive2, orCreateCancellableContinuation.getContext());
                                }
                            } else if (andIncrement < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                                channelSegment2.cleanPrev();
                            }
                        }
                    } else {
                        channelSegment.cleanPrev();
                        this.receiveResult = updateCellReceive;
                        this.continuation = null;
                        boxBoolean = Boxing.boxBoolean(true);
                        Function1 function13 = bufferedChannel.onUndeliveredElement;
                        if (function13 != null) {
                            function1 = OnUndeliveredElementKt.bindCancellationFun(function13, updateCellReceive, orCreateCancellableContinuation.getContext());
                        }
                    }
                    orCreateCancellableContinuation.resume(boxBoolean, function1);
                }
                Object result = orCreateCancellableContinuation.getResult();
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (result == coroutine_suspended) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return result;
            } catch (Throwable th) {
                orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                throw th;
            }
        }

        private final boolean onClosedHasNext() {
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable closeCause = BufferedChannel.this.getCloseCause();
            if (closeCause == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(closeCause);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void onClosedHasNextNoWaiterSuspend() {
            Object createFailure;
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable closeCause = BufferedChannel.this.getCloseCause();
            if (closeCause == null) {
                Result.Companion companion = Result.Companion;
                createFailure = Boolean.FALSE;
            } else {
                Result.Companion companion2 = Result.Companion;
                createFailure = ResultKt.createFailure(closeCause);
            }
            cancellableContinuationImpl.resumeWith(Result.m45constructorimpl(createFailure));
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object hasNext(Continuation continuation) {
            boolean onClosedHasNext;
            ChannelSegment channelSegment;
            Symbol symbol;
            Symbol symbol2;
            Symbol symbol3;
            BufferedChannel bufferedChannel = BufferedChannel.this;
            ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.receiveSegment$FU.get(bufferedChannel);
            while (true) {
                if (bufferedChannel.isClosedForReceive()) {
                    onClosedHasNext = onClosedHasNext();
                    break;
                }
                long andIncrement = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel);
                int i = BufferedChannelKt.SEGMENT_SIZE;
                long j = andIncrement / i;
                int i2 = (int) (andIncrement % i);
                if (channelSegment2.id != j) {
                    ChannelSegment findSegmentReceive = bufferedChannel.findSegmentReceive(j, channelSegment2);
                    if (findSegmentReceive == null) {
                        continue;
                    } else {
                        channelSegment = findSegmentReceive;
                    }
                } else {
                    channelSegment = channelSegment2;
                }
                Object updateCellReceive = bufferedChannel.updateCellReceive(channelSegment, i2, andIncrement, null);
                symbol = BufferedChannelKt.SUSPEND;
                if (updateCellReceive == symbol) {
                    throw new IllegalStateException("unreachable".toString());
                }
                symbol2 = BufferedChannelKt.FAILED;
                if (updateCellReceive == symbol2) {
                    if (andIncrement < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                        channelSegment.cleanPrev();
                    }
                    channelSegment2 = channelSegment;
                } else {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updateCellReceive == symbol3) {
                        return hasNextOnNoWaiterSuspend(channelSegment, i2, andIncrement, continuation);
                    }
                    channelSegment.cleanPrev();
                    this.receiveResult = updateCellReceive;
                    onClosedHasNext = true;
                }
            }
            return Boxing.boxBoolean(onClosedHasNext);
        }

        @Override // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment segment, int i) {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.invokeOnCancellation(segment, i);
            }
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object next() {
            Symbol symbol;
            Symbol symbol2;
            Object obj = this.receiveResult;
            symbol = BufferedChannelKt.NO_RECEIVE_RESULT;
            if (!(obj != symbol)) {
                throw new IllegalStateException("`hasNext()` has not been invoked".toString());
            }
            symbol2 = BufferedChannelKt.NO_RECEIVE_RESULT;
            this.receiveResult = symbol2;
            if (obj != BufferedChannelKt.getCHANNEL_CLOSED()) {
                return obj;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(BufferedChannel.this.getReceiveException());
        }

        public final boolean tryResumeHasNext(Object obj) {
            boolean tryResume0;
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl);
            this.continuation = null;
            this.receiveResult = obj;
            Boolean bool = Boolean.TRUE;
            Function1 function1 = BufferedChannel.this.onUndeliveredElement;
            tryResume0 = BufferedChannelKt.tryResume0(cancellableContinuationImpl, bool, function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, obj, cancellableContinuationImpl.getContext()) : null);
            return tryResume0;
        }

        public final void tryResumeHasNextOnClosedChannel() {
            Object createFailure;
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable closeCause = BufferedChannel.this.getCloseCause();
            if (closeCause == null) {
                Result.Companion companion = Result.Companion;
                createFailure = Boolean.FALSE;
            } else {
                Result.Companion companion2 = Result.Companion;
                createFailure = ResultKt.createFailure(closeCause);
            }
            cancellableContinuationImpl.resumeWith(Result.m45constructorimpl(createFailure));
        }
    }

    public BufferedChannel(int i, Function1 function1) {
        long initialBufferEnd;
        Symbol symbol;
        this.capacity = i;
        this.onUndeliveredElement = function1;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Invalid channel capacity: " + i + ", should be >=0").toString());
        }
        initialBufferEnd = BufferedChannelKt.initialBufferEnd(i);
        this.bufferEnd = initialBufferEnd;
        this.completedExpandBuffersAndPauseFlag = getBufferEndCounter();
        ChannelSegment channelSegment = new ChannelSegment(0L, null, this, 3);
        this.sendSegment = channelSegment;
        this.receiveSegment = channelSegment;
        if (isRendezvousOrUnlimited()) {
            channelSegment = BufferedChannelKt.NULL_SEGMENT;
            Intrinsics.checkNotNull(channelSegment, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment = channelSegment;
        this.onUndeliveredElementReceiveCancellationConstructor = function1 != null ? new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                return invoke((SelectInstance) null, obj2, obj3);
            }

            public final Function1 invoke(SelectInstance selectInstance, Object obj, Object obj2) {
                return new Function1(obj2, BufferedChannel.this, selectInstance) { // from class: kotlinx.coroutines.channels.BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1.1
                    final /* synthetic */ Object $element;
                    final /* synthetic */ BufferedChannel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj3) {
                        invoke((Throwable) obj3);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Throwable th) {
                        if (this.$element == BufferedChannelKt.getCHANNEL_CLOSED()) {
                            return;
                        }
                        Function1 function12 = this.this$0.onUndeliveredElement;
                        throw null;
                    }
                };
            }
        } : null;
        symbol = BufferedChannelKt.NO_CLOSE_CAUSE;
        this._closeCause = symbol;
    }

    private final boolean bufferOrRendezvousSend(long j) {
        return j < getBufferEndCounter() || j < getReceiversCounter$kotlinx_coroutines_core() + ((long) this.capacity);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0039, code lost:
    
        r0 = kotlinx.coroutines.internal.InlineList.m75plusFjFbRPM(r0, r4);
        r9.onCancelledRequest(r3, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void cancelSuspendedReceiveRequests(kotlinx.coroutines.channels.ChannelSegment r9, long r10) {
        /*
            r8 = this;
            r0 = 0
            r1 = 1
            java.lang.Object r0 = kotlinx.coroutines.internal.InlineList.m74constructorimpl$default(r0, r1, r0)
        L6:
            r2 = -1
            if (r9 == 0) goto L67
            int r3 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            int r3 = r3 - r1
        Lc:
            if (r2 >= r3) goto L60
            long r4 = r9.id
            int r6 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r6 = (long) r6
            long r4 = r4 * r6
            long r6 = (long) r3
            long r4 = r4 + r6
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 < 0) goto L67
        L1a:
            java.lang.Object r4 = r9.getState$kotlinx_coroutines_core(r3)
            if (r4 == 0) goto L50
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.BufferedChannelKt.access$getIN_BUFFER$p()
            if (r4 != r5) goto L27
            goto L50
        L27:
            boolean r5 = r4 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r5 == 0) goto L41
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r5 = r9.casState$kotlinx_coroutines_core(r3, r4, r5)
            if (r5 == 0) goto L1a
            kotlinx.coroutines.channels.WaiterEB r4 = (kotlinx.coroutines.channels.WaiterEB) r4
            kotlinx.coroutines.Waiter r4 = r4.waiter
        L39:
            java.lang.Object r0 = kotlinx.coroutines.internal.InlineList.m75plusFjFbRPM(r0, r4)
            r9.onCancelledRequest(r3, r1)
            goto L5d
        L41:
            boolean r5 = r4 instanceof kotlinx.coroutines.Waiter
            if (r5 == 0) goto L5d
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r5 = r9.casState$kotlinx_coroutines_core(r3, r4, r5)
            if (r5 == 0) goto L1a
            goto L39
        L50:
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r4 = r9.casState$kotlinx_coroutines_core(r3, r4, r5)
            if (r4 == 0) goto L1a
            r9.onSlotCleaned()
        L5d:
            int r3 = r3 + (-1)
            goto Lc
        L60:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r9 = r9.getPrev()
            kotlinx.coroutines.channels.ChannelSegment r9 = (kotlinx.coroutines.channels.ChannelSegment) r9
            goto L6
        L67:
            if (r0 == 0) goto L8d
            boolean r9 = r0 instanceof java.util.ArrayList
            if (r9 != 0) goto L73
            kotlinx.coroutines.Waiter r0 = (kotlinx.coroutines.Waiter) r0
            r8.resumeReceiverOnClosedChannel(r0)
            goto L8d
        L73:
            java.lang.String r9 = "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r9)
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r9 = r0.size()
            int r9 = r9 - r1
        L7f:
            if (r2 >= r9) goto L8d
            java.lang.Object r10 = r0.get(r9)
            kotlinx.coroutines.Waiter r10 = (kotlinx.coroutines.Waiter) r10
            r8.resumeReceiverOnClosedChannel(r10)
            int r9 = r9 + (-1)
            goto L7f
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.cancelSuspendedReceiveRequests(kotlinx.coroutines.channels.ChannelSegment, long):void");
    }

    private final ChannelSegment closeLinkedList() {
        Object obj = bufferEndSegment$FU.get(this);
        ChannelSegment channelSegment = (ChannelSegment) sendSegment$FU.get(this);
        if (channelSegment.id > ((ChannelSegment) obj).id) {
            obj = channelSegment;
        }
        ChannelSegment channelSegment2 = (ChannelSegment) receiveSegment$FU.get(this);
        if (channelSegment2.id > ((ChannelSegment) obj).id) {
            obj = channelSegment2;
        }
        return (ChannelSegment) ConcurrentLinkedListKt.close((ConcurrentLinkedListNode) obj);
    }

    private final void completeCancel(long j) {
        removeUnprocessedElements(completeClose(j));
    }

    private final ChannelSegment completeClose(long j) {
        ChannelSegment closeLinkedList = closeLinkedList();
        if (isConflatedDropOldest()) {
            long markAllEmptyCellsAsClosed = markAllEmptyCellsAsClosed(closeLinkedList);
            if (markAllEmptyCellsAsClosed != -1) {
                dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(markAllEmptyCellsAsClosed);
            }
        }
        cancelSuspendedReceiveRequests(closeLinkedList, j);
        return closeLinkedList;
    }

    private final void completeCloseOrCancel() {
        isClosedForSend();
    }

    private final void expandBuffer() {
        if (isRendezvousOrUnlimited()) {
            return;
        }
        ChannelSegment channelSegment = (ChannelSegment) bufferEndSegment$FU.get(this);
        while (true) {
            long andIncrement = bufferEnd$FU.getAndIncrement(this);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j = andIncrement / i;
            long sendersCounter$kotlinx_coroutines_core = getSendersCounter$kotlinx_coroutines_core();
            long j2 = channelSegment.id;
            if (sendersCounter$kotlinx_coroutines_core <= andIncrement) {
                if (j2 < j && channelSegment.getNext() != null) {
                    moveSegmentBufferEndToSpecifiedOrLast(j, channelSegment);
                }
                incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
                return;
            }
            if (j2 != j) {
                ChannelSegment findSegmentBufferEnd = findSegmentBufferEnd(j, channelSegment, andIncrement);
                if (findSegmentBufferEnd == null) {
                    continue;
                } else {
                    channelSegment = findSegmentBufferEnd;
                }
            }
            boolean updateCellExpandBuffer = updateCellExpandBuffer(channelSegment, (int) (andIncrement % i), andIncrement);
            incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
            if (updateCellExpandBuffer) {
                return;
            }
        }
    }

    private final ChannelSegment findSegmentBufferEnd(long j, ChannelSegment channelSegment, long j2) {
        Object findSegmentInternal;
        boolean z;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = bufferEndSegment$FU;
        Function2 function2 = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, j, function2);
            if (SegmentOrClosed.m80isClosedimpl(findSegmentInternal)) {
                break;
            }
            Segment m79getSegmentimpl = SegmentOrClosed.m79getSegmentimpl(findSegmentInternal);
            while (true) {
                Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                if (segment.id >= m79getSegmentimpl.id) {
                    break;
                }
                if (!m79getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, m79getSegmentimpl)) {
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                } else if (m79getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                    m79getSegmentimpl.remove();
                }
            }
            z = true;
        } while (!z);
        if (SegmentOrClosed.m80isClosedimpl(findSegmentInternal)) {
            completeCloseOrCancel();
            moveSegmentBufferEndToSpecifiedOrLast(j, channelSegment);
        } else {
            ChannelSegment channelSegment2 = (ChannelSegment) SegmentOrClosed.m79getSegmentimpl(findSegmentInternal);
            long j3 = channelSegment2.id;
            if (j3 <= j) {
                return channelSegment2;
            }
            int i = BufferedChannelKt.SEGMENT_SIZE;
            if (bufferEnd$FU.compareAndSet(this, j2 + 1, i * j3)) {
                incCompletedExpandBufferAttempts((channelSegment2.id * i) - j2);
                return null;
            }
        }
        incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment findSegmentReceive(long j, ChannelSegment channelSegment) {
        Object findSegmentInternal;
        boolean z;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = receiveSegment$FU;
        Function2 function2 = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, j, function2);
            if (!SegmentOrClosed.m80isClosedimpl(findSegmentInternal)) {
                Segment m79getSegmentimpl = SegmentOrClosed.m79getSegmentimpl(findSegmentInternal);
                while (true) {
                    Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                    z = true;
                    if (segment.id >= m79getSegmentimpl.id) {
                        break;
                    }
                    if (!m79getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                        z = false;
                        break;
                    }
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, m79getSegmentimpl)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (m79getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                        m79getSegmentimpl.remove();
                    }
                }
            } else {
                break;
            }
        } while (!z);
        if (SegmentOrClosed.m80isClosedimpl(findSegmentInternal)) {
            completeCloseOrCancel();
            if (channelSegment.id * BufferedChannelKt.SEGMENT_SIZE >= getSendersCounter$kotlinx_coroutines_core()) {
                return null;
            }
        } else {
            channelSegment = (ChannelSegment) SegmentOrClosed.m79getSegmentimpl(findSegmentInternal);
            if (!isRendezvousOrUnlimited() && j <= getBufferEndCounter() / BufferedChannelKt.SEGMENT_SIZE) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = bufferEndSegment$FU;
                while (true) {
                    Segment segment2 = (Segment) atomicReferenceFieldUpdater2.get(this);
                    if (segment2.id >= channelSegment.id || !channelSegment.tryIncPointers$kotlinx_coroutines_core()) {
                        break;
                    }
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater2, this, segment2, channelSegment)) {
                        if (segment2.decPointers$kotlinx_coroutines_core()) {
                            segment2.remove();
                        }
                    } else if (channelSegment.decPointers$kotlinx_coroutines_core()) {
                        channelSegment.remove();
                    }
                }
            }
            long j2 = channelSegment.id;
            if (j2 <= j) {
                return channelSegment;
            }
            int i = BufferedChannelKt.SEGMENT_SIZE;
            updateReceiversCounterIfLower(j2 * i);
            if (channelSegment.id * i >= getSendersCounter$kotlinx_coroutines_core()) {
                return null;
            }
        }
        channelSegment.cleanPrev();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment findSegmentSend(long j, ChannelSegment channelSegment) {
        Object findSegmentInternal;
        boolean z;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = sendSegment$FU;
        Function2 function2 = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, j, function2);
            if (!SegmentOrClosed.m80isClosedimpl(findSegmentInternal)) {
                Segment m79getSegmentimpl = SegmentOrClosed.m79getSegmentimpl(findSegmentInternal);
                while (true) {
                    Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                    z = true;
                    if (segment.id >= m79getSegmentimpl.id) {
                        break;
                    }
                    if (!m79getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                        z = false;
                        break;
                    }
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, m79getSegmentimpl)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (m79getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                        m79getSegmentimpl.remove();
                    }
                }
            } else {
                break;
            }
        } while (!z);
        if (SegmentOrClosed.m80isClosedimpl(findSegmentInternal)) {
            completeCloseOrCancel();
            if (channelSegment.id * BufferedChannelKt.SEGMENT_SIZE >= getReceiversCounter$kotlinx_coroutines_core()) {
                return null;
            }
        } else {
            channelSegment = (ChannelSegment) SegmentOrClosed.m79getSegmentimpl(findSegmentInternal);
            long j2 = channelSegment.id;
            if (j2 <= j) {
                return channelSegment;
            }
            int i = BufferedChannelKt.SEGMENT_SIZE;
            updateSendersCounterIfLower(j2 * i);
            if (channelSegment.id * i >= getReceiversCounter$kotlinx_coroutines_core()) {
                return null;
            }
        }
        channelSegment.cleanPrev();
        return null;
    }

    private final long getBufferEndCounter() {
        return bufferEnd$FU.get(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Throwable getReceiveException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedReceiveChannelException("Channel was closed") : closeCause;
    }

    private final void incCompletedExpandBufferAttempts(long j) {
        if (!((completedExpandBuffersAndPauseFlag$FU.addAndGet(this, j) & 4611686018427387904L) != 0)) {
            return;
        }
        do {
        } while ((completedExpandBuffersAndPauseFlag$FU.get(this) & 4611686018427387904L) != 0);
    }

    static /* synthetic */ void incCompletedExpandBufferAttempts$default(BufferedChannel bufferedChannel, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
        }
        if ((i & 1) != 0) {
            j = 1;
        }
        bufferedChannel.incCompletedExpandBufferAttempts(j);
    }

    private final void invokeCloseHandler() {
        Object obj;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = closeHandler$FU;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, obj, obj == null ? BufferedChannelKt.CLOSE_HANDLER_CLOSED : BufferedChannelKt.CLOSE_HANDLER_INVOKED));
        if (obj == null) {
            return;
        }
        ((Function1) obj).invoke(getCloseCause());
    }

    private final boolean isCellNonEmpty(ChannelSegment channelSegment, int i, long j) {
        Object state$kotlinx_coroutines_core;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        do {
            state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
            if (state$kotlinx_coroutines_core != null) {
                symbol2 = BufferedChannelKt.IN_BUFFER;
                if (state$kotlinx_coroutines_core != symbol2) {
                    if (state$kotlinx_coroutines_core == BufferedChannelKt.BUFFERED) {
                        return true;
                    }
                    symbol3 = BufferedChannelKt.INTERRUPTED_SEND;
                    if (state$kotlinx_coroutines_core == symbol3 || state$kotlinx_coroutines_core == BufferedChannelKt.getCHANNEL_CLOSED()) {
                        return false;
                    }
                    symbol4 = BufferedChannelKt.DONE_RCV;
                    if (state$kotlinx_coroutines_core == symbol4) {
                        return false;
                    }
                    symbol5 = BufferedChannelKt.POISONED;
                    if (state$kotlinx_coroutines_core == symbol5) {
                        return false;
                    }
                    symbol6 = BufferedChannelKt.RESUMING_BY_EB;
                    if (state$kotlinx_coroutines_core == symbol6) {
                        return true;
                    }
                    symbol7 = BufferedChannelKt.RESUMING_BY_RCV;
                    return state$kotlinx_coroutines_core != symbol7 && j == getReceiversCounter$kotlinx_coroutines_core();
                }
            }
            symbol = BufferedChannelKt.POISONED;
        } while (!channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, symbol));
        expandBuffer();
        return false;
    }

    private final boolean isClosed(long j, boolean z) {
        int i = (int) (j >> 60);
        if (i == 0 || i == 1) {
            return false;
        }
        if (i == 2) {
            completeClose(j & 1152921504606846975L);
            if (z && hasElements$kotlinx_coroutines_core()) {
                return false;
            }
        } else {
            if (i != 3) {
                throw new IllegalStateException(("unexpected close status: " + i).toString());
            }
            completeCancel(j & 1152921504606846975L);
        }
        return true;
    }

    private final boolean isClosedForReceive0(long j) {
        return isClosed(j, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isClosedForSend0(long j) {
        return isClosed(j, false);
    }

    private final boolean isRendezvousOrUnlimited() {
        long bufferEndCounter = getBufferEndCounter();
        return bufferEndCounter == 0 || bufferEndCounter == Long.MAX_VALUE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x003c, code lost:
    
        r8 = (kotlinx.coroutines.channels.ChannelSegment) r8.getPrev();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final long markAllEmptyCellsAsClosed(kotlinx.coroutines.channels.ChannelSegment r8) {
        /*
            r7 = this;
        L0:
            int r0 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            int r0 = r0 + (-1)
        L4:
            r1 = -1
            r3 = -1
            if (r3 >= r0) goto L3c
            long r3 = r8.id
            int r5 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r5 = (long) r5
            long r3 = r3 * r5
            long r5 = (long) r0
            long r3 = r3 + r5
            long r5 = r7.getReceiversCounter$kotlinx_coroutines_core()
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L1a
            return r1
        L1a:
            java.lang.Object r1 = r8.getState$kotlinx_coroutines_core(r0)
            if (r1 == 0) goto L2c
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.access$getIN_BUFFER$p()
            if (r1 != r2) goto L27
            goto L2c
        L27:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r1 != r2) goto L39
            return r3
        L2c:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r1 = r8.casState$kotlinx_coroutines_core(r0, r1, r2)
            if (r1 == 0) goto L1a
            r8.onSlotCleaned()
        L39:
            int r0 = r0 + (-1)
            goto L4
        L3c:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r8 = r8.getPrev()
            kotlinx.coroutines.channels.ChannelSegment r8 = (kotlinx.coroutines.channels.ChannelSegment) r8
            if (r8 != 0) goto L0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.markAllEmptyCellsAsClosed(kotlinx.coroutines.channels.ChannelSegment):long");
    }

    private final void markCancellationStarted() {
        long j;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater atomicLongFieldUpdater = sendersAndCloseStatus$FU;
        do {
            j = atomicLongFieldUpdater.get(this);
            if (((int) (j >> 60)) != 0) {
                return;
            } else {
                constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & j, 1);
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j, constructSendersAndCloseStatus));
    }

    private final void markCancelled() {
        long j;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater atomicLongFieldUpdater = sendersAndCloseStatus$FU;
        do {
            j = atomicLongFieldUpdater.get(this);
            constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & j, 3);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j, constructSendersAndCloseStatus));
    }

    private final void markClosed() {
        long j;
        long j2;
        int i;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater atomicLongFieldUpdater = sendersAndCloseStatus$FU;
        do {
            j = atomicLongFieldUpdater.get(this);
            int i2 = (int) (j >> 60);
            if (i2 == 0) {
                j2 = j & 1152921504606846975L;
                i = 2;
            } else {
                if (i2 != 1) {
                    return;
                }
                j2 = j & 1152921504606846975L;
                i = 3;
            }
            constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(j2, i);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j, constructSendersAndCloseStatus));
    }

    private final void moveSegmentBufferEndToSpecifiedOrLast(long j, ChannelSegment channelSegment) {
        boolean z;
        ChannelSegment channelSegment2;
        ChannelSegment channelSegment3;
        while (channelSegment.id < j && (channelSegment3 = (ChannelSegment) channelSegment.getNext()) != null) {
            channelSegment = channelSegment3;
        }
        while (true) {
            if (!channelSegment.isRemoved() || (channelSegment2 = (ChannelSegment) channelSegment.getNext()) == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = bufferEndSegment$FU;
                while (true) {
                    Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                    z = true;
                    if (segment.id >= channelSegment.id) {
                        break;
                    }
                    if (!channelSegment.tryIncPointers$kotlinx_coroutines_core()) {
                        z = false;
                        break;
                    } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, channelSegment)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (channelSegment.decPointers$kotlinx_coroutines_core()) {
                        channelSegment.remove();
                    }
                }
                if (z) {
                    return;
                }
            } else {
                channelSegment = channelSegment2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedReceiveCatchingOnNoWaiterSuspend(CancellableContinuation cancellableContinuation) {
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m45constructorimpl(ChannelResult.m57boximpl(ChannelResult.Companion.m67closedJP2dKIU(getCloseCause()))));
    }

    private final Object onClosedSend(Object obj, Continuation continuation) {
        Continuation intercepted;
        Object createFailure;
        Object coroutine_suspended;
        Object coroutine_suspended2;
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        cancellableContinuationImpl.initCancellability();
        Function1 function1 = this.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, obj, null, 2, null)) == null) {
            Throwable sendException = getSendException();
            Result.Companion companion = Result.Companion;
            createFailure = ResultKt.createFailure(sendException);
        } else {
            ExceptionsKt__ExceptionsKt.addSuppressed(callUndeliveredElementCatchingException$default, getSendException());
            Result.Companion companion2 = Result.Companion;
            createFailure = ResultKt.createFailure(callUndeliveredElementCatchingException$default);
        }
        cancellableContinuationImpl.resumeWith(Result.m45constructorimpl(createFailure));
        Object result = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return result == coroutine_suspended2 ? result : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedSendOnNoWaiterSuspend(Object obj, CancellableContinuation cancellableContinuation) {
        Function1 function1 = this.onUndeliveredElement;
        if (function1 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function1, obj, cancellableContinuation.getContext());
        }
        Throwable sendException = getSendException();
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m45constructorimpl(ResultKt.createFailure(sendException)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareReceiverForSuspension(Waiter waiter, ChannelSegment channelSegment, int i) {
        onReceiveEnqueued();
        waiter.invokeOnCancellation(channelSegment, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareSenderForSuspension(Waiter waiter, ChannelSegment channelSegment, int i) {
        waiter.invokeOnCancellation(channelSegment, i + BufferedChannelKt.SEGMENT_SIZE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: receiveCatching-JP2dKIU$suspendImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object m52receiveCatchingJP2dKIU$suspendImpl(kotlinx.coroutines.channels.BufferedChannel r14, kotlin.coroutines.Continuation r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1
            if (r0 == 0) goto L13
            r0 = r15
            kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1 r0 = (kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1 r0 = new kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1
            r0.<init>(r14, r15)
        L18:
            r6 = r0
            java.lang.Object r15 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L39
            if (r1 != r2) goto L31
            kotlin.ResultKt.throwOnFailure(r15)
            kotlinx.coroutines.channels.ChannelResult r15 = (kotlinx.coroutines.channels.ChannelResult) r15
            java.lang.Object r14 = r15.m66unboximpl()
            goto Lb6
        L31:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L39:
            kotlin.ResultKt.throwOnFailure(r15)
            r15 = 0
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = access$getReceiveSegment$FU$p()
            java.lang.Object r1 = r1.get(r14)
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1
        L47:
            boolean r3 = r14.isClosedForReceive()
            if (r3 == 0) goto L59
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.Companion
            java.lang.Throwable r14 = r14.getCloseCause()
            java.lang.Object r14 = r15.m67closedJP2dKIU(r14)
            goto Lb6
        L59:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r3 = access$getReceivers$FU$p()
            long r4 = r3.getAndIncrement(r14)
            int r3 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r7 = (long) r3
            long r7 = r4 / r7
            long r9 = (long) r3
            long r9 = r4 % r9
            int r3 = (int) r9
            long r9 = r1.id
            int r9 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r9 == 0) goto L79
            kotlinx.coroutines.channels.ChannelSegment r7 = access$findSegmentReceive(r14, r7, r1)
            if (r7 != 0) goto L77
            goto L47
        L77:
            r13 = r7
            goto L7a
        L79:
            r13 = r1
        L7a:
            r7 = r14
            r8 = r13
            r9 = r3
            r10 = r4
            r12 = r15
            java.lang.Object r1 = access$updateCellReceive(r7, r8, r9, r10, r12)
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.access$getSUSPEND$p()
            if (r1 == r7) goto Lb7
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.access$getFAILED$p()
            if (r1 != r7) goto L9c
            long r7 = r14.getSendersCounter$kotlinx_coroutines_core()
            int r1 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r1 >= 0) goto L9a
            r13.cleanPrev()
        L9a:
            r1 = r13
            goto L47
        L9c:
            kotlinx.coroutines.internal.Symbol r15 = kotlinx.coroutines.channels.BufferedChannelKt.access$getSUSPEND_NO_WAITER$p()
            if (r1 != r15) goto Lad
            r6.label = r2
            r1 = r14
            r2 = r13
            java.lang.Object r14 = r1.m53receiveCatchingOnNoWaiterSuspendGKJJFZk(r2, r3, r4, r6)
            if (r14 != r0) goto Lb6
            return r0
        Lad:
            r13.cleanPrev()
            kotlinx.coroutines.channels.ChannelResult$Companion r14 = kotlinx.coroutines.channels.ChannelResult.Companion
            java.lang.Object r14 = r14.m69successJP2dKIU(r1)
        Lb6:
            return r14
        Lb7:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "unexpected"
            java.lang.String r15 = r15.toString()
            r14.<init>(r15)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m52receiveCatchingJP2dKIU$suspendImpl(kotlinx.coroutines.channels.BufferedChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* renamed from: receiveCatchingOnNoWaiterSuspend-GKJJFZk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m53receiveCatchingOnNoWaiterSuspendGKJJFZk(kotlinx.coroutines.channels.ChannelSegment r11, int r12, long r13, kotlin.coroutines.Continuation r15) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m53receiveCatchingOnNoWaiterSuspendGKJJFZk(kotlinx.coroutines.channels.ChannelSegment, int, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:81:0x00aa, code lost:
    
        r12 = (kotlinx.coroutines.channels.ChannelSegment) r12.getPrev();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void removeUnprocessedElements(kotlinx.coroutines.channels.ChannelSegment r12) {
        /*
            r11 = this;
            kotlin.jvm.functions.Function1 r0 = r11.onUndeliveredElement
            r1 = 0
            r2 = 1
            java.lang.Object r3 = kotlinx.coroutines.internal.InlineList.m74constructorimpl$default(r1, r2, r1)
        L8:
            int r4 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            int r4 = r4 - r2
        Lb:
            r5 = -1
            if (r5 >= r4) goto Laa
            long r6 = r12.id
            int r8 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r8 = (long) r8
            long r6 = r6 * r8
            long r8 = (long) r4
            long r6 = r6 + r8
        L16:
            java.lang.Object r8 = r12.getState$kotlinx_coroutines_core(r4)
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.access$getDONE_RCV$p()
            if (r8 == r9) goto Lb2
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r8 != r9) goto L47
            long r9 = r11.getReceiversCounter$kotlinx_coroutines_core()
            int r9 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r9 < 0) goto Lb2
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r8 = r12.casState$kotlinx_coroutines_core(r4, r8, r9)
            if (r8 == 0) goto L16
            if (r0 == 0) goto L40
            java.lang.Object r5 = r12.getElement$kotlinx_coroutines_core(r4)
            kotlinx.coroutines.internal.UndeliveredElementException r1 = kotlinx.coroutines.internal.OnUndeliveredElementKt.callUndeliveredElementCatchingException(r0, r5, r1)
        L40:
            r12.cleanElement$kotlinx_coroutines_core(r4)
        L43:
            r12.onSlotCleaned()
            goto La6
        L47:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.access$getIN_BUFFER$p()
            if (r8 == r9) goto L9b
            if (r8 != 0) goto L50
            goto L9b
        L50:
            boolean r9 = r8 instanceof kotlinx.coroutines.Waiter
            if (r9 != 0) goto L6d
            boolean r9 = r8 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r9 == 0) goto L59
            goto L6d
        L59:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.access$getRESUMING_BY_EB$p()
            if (r8 == r9) goto Lb2
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.access$getRESUMING_BY_RCV$p()
            if (r8 != r9) goto L66
            goto Lb2
        L66:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.access$getRESUMING_BY_EB$p()
            if (r8 == r9) goto L16
            goto La6
        L6d:
            long r9 = r11.getReceiversCounter$kotlinx_coroutines_core()
            int r9 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r9 < 0) goto Lb2
            boolean r9 = r8 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r9 == 0) goto L7f
            r9 = r8
            kotlinx.coroutines.channels.WaiterEB r9 = (kotlinx.coroutines.channels.WaiterEB) r9
            kotlinx.coroutines.Waiter r9 = r9.waiter
            goto L82
        L7f:
            r9 = r8
            kotlinx.coroutines.Waiter r9 = (kotlinx.coroutines.Waiter) r9
        L82:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r8 = r12.casState$kotlinx_coroutines_core(r4, r8, r10)
            if (r8 == 0) goto L16
            if (r0 == 0) goto L96
            java.lang.Object r5 = r12.getElement$kotlinx_coroutines_core(r4)
            kotlinx.coroutines.internal.UndeliveredElementException r1 = kotlinx.coroutines.internal.OnUndeliveredElementKt.callUndeliveredElementCatchingException(r0, r5, r1)
        L96:
            java.lang.Object r3 = kotlinx.coroutines.internal.InlineList.m75plusFjFbRPM(r3, r9)
            goto L40
        L9b:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r8 = r12.casState$kotlinx_coroutines_core(r4, r8, r9)
            if (r8 == 0) goto L16
            goto L43
        La6:
            int r4 = r4 + (-1)
            goto Lb
        Laa:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r12 = r12.getPrev()
            kotlinx.coroutines.channels.ChannelSegment r12 = (kotlinx.coroutines.channels.ChannelSegment) r12
            if (r12 != 0) goto L8
        Lb2:
            if (r3 == 0) goto Ld8
            boolean r12 = r3 instanceof java.util.ArrayList
            if (r12 != 0) goto Lbe
            kotlinx.coroutines.Waiter r3 = (kotlinx.coroutines.Waiter) r3
            r11.resumeSenderOnCancelledChannel(r3)
            goto Ld8
        Lbe:
            java.lang.String r12 = "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r12)
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r12 = r3.size()
            int r12 = r12 - r2
        Lca:
            if (r5 >= r12) goto Ld8
            java.lang.Object r0 = r3.get(r12)
            kotlinx.coroutines.Waiter r0 = (kotlinx.coroutines.Waiter) r0
            r11.resumeSenderOnCancelledChannel(r0)
            int r12 = r12 + (-1)
            goto Lca
        Ld8:
            if (r1 != 0) goto Ldb
            return
        Ldb:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.removeUnprocessedElements(kotlinx.coroutines.channels.ChannelSegment):void");
    }

    private final void resumeReceiverOnClosedChannel(Waiter waiter) {
        resumeWaiterOnClosedChannel(waiter, true);
    }

    private final void resumeSenderOnCancelledChannel(Waiter waiter) {
        resumeWaiterOnClosedChannel(waiter, false);
    }

    private final void resumeWaiterOnClosedChannel(Waiter waiter, boolean z) {
        Continuation continuation;
        Object m57boximpl;
        if (waiter instanceof CancellableContinuation) {
            continuation = (Continuation) waiter;
            Result.Companion companion = Result.Companion;
            m57boximpl = ResultKt.createFailure(z ? getReceiveException() : getSendException());
        } else {
            if (!(waiter instanceof ReceiveCatching)) {
                if (waiter instanceof BufferedChannelIterator) {
                    ((BufferedChannelIterator) waiter).tryResumeHasNextOnClosedChannel();
                    return;
                }
                throw new IllegalStateException(("Unexpected waiter: " + waiter).toString());
            }
            continuation = ((ReceiveCatching) waiter).cont;
            Result.Companion companion2 = Result.Companion;
            m57boximpl = ChannelResult.m57boximpl(ChannelResult.Companion.m67closedJP2dKIU(getCloseCause()));
        }
        continuation.resumeWith(Result.m45constructorimpl(m57boximpl));
    }

    static /* synthetic */ Object send$suspendImpl(BufferedChannel bufferedChannel, Object obj, Continuation continuation) {
        ChannelSegment channelSegment;
        Object coroutine_suspended;
        Object coroutine_suspended2;
        Object coroutine_suspended3;
        Object coroutine_suspended4;
        ChannelSegment channelSegment2 = (ChannelSegment) sendSegment$FU.get(bufferedChannel);
        while (true) {
            long andIncrement = sendersAndCloseStatus$FU.getAndIncrement(bufferedChannel);
            long j = andIncrement & 1152921504606846975L;
            boolean isClosedForSend0 = bufferedChannel.isClosedForSend0(andIncrement);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j2 = j / i;
            int i2 = (int) (j % i);
            if (channelSegment2.id != j2) {
                ChannelSegment findSegmentSend = bufferedChannel.findSegmentSend(j2, channelSegment2);
                if (findSegmentSend != null) {
                    channelSegment = findSegmentSend;
                } else if (isClosedForSend0) {
                    Object onClosedSend = bufferedChannel.onClosedSend(obj, continuation);
                    coroutine_suspended4 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (onClosedSend == coroutine_suspended4) {
                        return onClosedSend;
                    }
                }
            } else {
                channelSegment = channelSegment2;
            }
            int updateCellSend = bufferedChannel.updateCellSend(channelSegment, i2, obj, j, null, isClosedForSend0);
            if (updateCellSend == 0) {
                channelSegment.cleanPrev();
                break;
            }
            if (updateCellSend == 1) {
                break;
            }
            if (updateCellSend != 2) {
                if (updateCellSend == 3) {
                    Object sendOnNoWaiterSuspend = bufferedChannel.sendOnNoWaiterSuspend(channelSegment, i2, obj, j, continuation);
                    coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (sendOnNoWaiterSuspend == coroutine_suspended2) {
                        return sendOnNoWaiterSuspend;
                    }
                } else if (updateCellSend != 4) {
                    if (updateCellSend == 5) {
                        channelSegment.cleanPrev();
                    }
                    channelSegment2 = channelSegment;
                } else {
                    if (j < bufferedChannel.getReceiversCounter$kotlinx_coroutines_core()) {
                        channelSegment.cleanPrev();
                    }
                    Object onClosedSend2 = bufferedChannel.onClosedSend(obj, continuation);
                    coroutine_suspended3 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (onClosedSend2 == coroutine_suspended3) {
                        return onClosedSend2;
                    }
                }
            } else if (isClosedForSend0) {
                channelSegment.onSlotCleaned();
                Object onClosedSend3 = bufferedChannel.onClosedSend(obj, continuation);
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (onClosedSend3 == coroutine_suspended) {
                    return onClosedSend3;
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0122 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0123  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Object sendOnNoWaiterSuspend(kotlinx.coroutines.channels.ChannelSegment r21, int r22, java.lang.Object r23, long r24, kotlin.coroutines.Continuation r26) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.sendOnNoWaiterSuspend(kotlinx.coroutines.channels.ChannelSegment, int, java.lang.Object, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean shouldSendSuspend(long j) {
        if (isClosedForSend0(j)) {
            return false;
        }
        return !bufferOrRendezvousSend(j & 1152921504606846975L);
    }

    private final boolean tryResumeReceiver(Object obj, Object obj2) {
        boolean tryResume0;
        boolean tryResume02;
        if (obj instanceof ReceiveCatching) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            ReceiveCatching receiveCatching = (ReceiveCatching) obj;
            CancellableContinuationImpl cancellableContinuationImpl = receiveCatching.cont;
            ChannelResult m57boximpl = ChannelResult.m57boximpl(ChannelResult.Companion.m69successJP2dKIU(obj2));
            Function1 function1 = this.onUndeliveredElement;
            tryResume02 = BufferedChannelKt.tryResume0(cancellableContinuationImpl, m57boximpl, function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, obj2, receiveCatching.cont.getContext()) : null);
            return tryResume02;
        }
        if (obj instanceof BufferedChannelIterator) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
            return ((BufferedChannelIterator) obj).tryResumeHasNext(obj2);
        }
        if (!(obj instanceof CancellableContinuation)) {
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
        CancellableContinuation cancellableContinuation = (CancellableContinuation) obj;
        Function1 function12 = this.onUndeliveredElement;
        tryResume0 = BufferedChannelKt.tryResume0(cancellableContinuation, obj2, function12 != null ? OnUndeliveredElementKt.bindCancellationFun(function12, obj2, cancellableContinuation.getContext()) : null);
        return tryResume0;
    }

    private final boolean tryResumeSender(Object obj, ChannelSegment channelSegment, int i) {
        if (obj instanceof CancellableContinuation) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return BufferedChannelKt.tryResume0$default((CancellableContinuation) obj, Unit.INSTANCE, null, 2, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    private final boolean updateCellExpandBuffer(ChannelSegment channelSegment, int i, long j) {
        Symbol symbol;
        Symbol symbol2;
        Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
        if ((state$kotlinx_coroutines_core instanceof Waiter) && j >= receivers$FU.get(this)) {
            symbol = BufferedChannelKt.RESUMING_BY_EB;
            if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, symbol)) {
                if (tryResumeSender(state$kotlinx_coroutines_core, channelSegment, i)) {
                    channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.BUFFERED);
                    return true;
                }
                symbol2 = BufferedChannelKt.INTERRUPTED_SEND;
                channelSegment.setState$kotlinx_coroutines_core(i, symbol2);
                channelSegment.onCancelledRequest(i, false);
                return false;
            }
        }
        return updateCellExpandBufferSlow(channelSegment, i, j);
    }

    private final boolean updateCellExpandBufferSlow(ChannelSegment channelSegment, int i, long j) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        Symbol symbol8;
        while (true) {
            Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
            if (!(state$kotlinx_coroutines_core instanceof Waiter)) {
                symbol3 = BufferedChannelKt.INTERRUPTED_SEND;
                if (state$kotlinx_coroutines_core != symbol3) {
                    if (state$kotlinx_coroutines_core != null) {
                        if (state$kotlinx_coroutines_core != BufferedChannelKt.BUFFERED) {
                            symbol5 = BufferedChannelKt.POISONED;
                            if (state$kotlinx_coroutines_core == symbol5) {
                                break;
                            }
                            symbol6 = BufferedChannelKt.DONE_RCV;
                            if (state$kotlinx_coroutines_core == symbol6) {
                                break;
                            }
                            symbol7 = BufferedChannelKt.INTERRUPTED_RCV;
                            if (state$kotlinx_coroutines_core == symbol7 || state$kotlinx_coroutines_core == BufferedChannelKt.getCHANNEL_CLOSED()) {
                                return true;
                            }
                            symbol8 = BufferedChannelKt.RESUMING_BY_RCV;
                            if (state$kotlinx_coroutines_core != symbol8) {
                                throw new IllegalStateException(("Unexpected cell state: " + state$kotlinx_coroutines_core).toString());
                            }
                        } else {
                            return true;
                        }
                    } else {
                        symbol4 = BufferedChannelKt.IN_BUFFER;
                        if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, symbol4)) {
                            return true;
                        }
                    }
                } else {
                    return false;
                }
            } else if (j >= receivers$FU.get(this)) {
                symbol = BufferedChannelKt.RESUMING_BY_EB;
                if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, symbol)) {
                    if (tryResumeSender(state$kotlinx_coroutines_core, channelSegment, i)) {
                        channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.BUFFERED);
                        return true;
                    }
                    symbol2 = BufferedChannelKt.INTERRUPTED_SEND;
                    channelSegment.setState$kotlinx_coroutines_core(i, symbol2);
                    channelSegment.onCancelledRequest(i, false);
                    return false;
                }
            } else if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, new WaiterEB((Waiter) state$kotlinx_coroutines_core))) {
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object updateCellReceive(ChannelSegment channelSegment, int i, long j, Object obj) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
        if (state$kotlinx_coroutines_core == null) {
            if (j >= (sendersAndCloseStatus$FU.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    return symbol3;
                }
                if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, obj)) {
                    expandBuffer();
                    symbol2 = BufferedChannelKt.SUSPEND;
                    return symbol2;
                }
            }
        } else if (state$kotlinx_coroutines_core == BufferedChannelKt.BUFFERED) {
            symbol = BufferedChannelKt.DONE_RCV;
            if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, symbol)) {
                expandBuffer();
                return channelSegment.retrieveElement$kotlinx_coroutines_core(i);
            }
        }
        return updateCellReceiveSlow(channelSegment, i, j, obj);
    }

    private final Object updateCellReceiveSlow(ChannelSegment channelSegment, int i, long j, Object obj) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        Symbol symbol8;
        Symbol symbol9;
        Symbol symbol10;
        Symbol symbol11;
        Symbol symbol12;
        Symbol symbol13;
        Symbol symbol14;
        Symbol symbol15;
        Symbol symbol16;
        while (true) {
            Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
            if (state$kotlinx_coroutines_core != null) {
                symbol5 = BufferedChannelKt.IN_BUFFER;
                if (state$kotlinx_coroutines_core != symbol5) {
                    if (state$kotlinx_coroutines_core == BufferedChannelKt.BUFFERED) {
                        symbol6 = BufferedChannelKt.DONE_RCV;
                        if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, symbol6)) {
                            expandBuffer();
                            return channelSegment.retrieveElement$kotlinx_coroutines_core(i);
                        }
                    } else {
                        symbol7 = BufferedChannelKt.INTERRUPTED_SEND;
                        if (state$kotlinx_coroutines_core == symbol7) {
                            symbol8 = BufferedChannelKt.FAILED;
                            return symbol8;
                        }
                        symbol9 = BufferedChannelKt.POISONED;
                        if (state$kotlinx_coroutines_core == symbol9) {
                            symbol10 = BufferedChannelKt.FAILED;
                            return symbol10;
                        }
                        if (state$kotlinx_coroutines_core == BufferedChannelKt.getCHANNEL_CLOSED()) {
                            expandBuffer();
                            symbol11 = BufferedChannelKt.FAILED;
                            return symbol11;
                        }
                        symbol12 = BufferedChannelKt.RESUMING_BY_EB;
                        if (state$kotlinx_coroutines_core != symbol12) {
                            symbol13 = BufferedChannelKt.RESUMING_BY_RCV;
                            if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, symbol13)) {
                                boolean z = state$kotlinx_coroutines_core instanceof WaiterEB;
                                if (z) {
                                    state$kotlinx_coroutines_core = ((WaiterEB) state$kotlinx_coroutines_core).waiter;
                                }
                                if (tryResumeSender(state$kotlinx_coroutines_core, channelSegment, i)) {
                                    symbol16 = BufferedChannelKt.DONE_RCV;
                                    channelSegment.setState$kotlinx_coroutines_core(i, symbol16);
                                    expandBuffer();
                                    return channelSegment.retrieveElement$kotlinx_coroutines_core(i);
                                }
                                symbol14 = BufferedChannelKt.INTERRUPTED_SEND;
                                channelSegment.setState$kotlinx_coroutines_core(i, symbol14);
                                channelSegment.onCancelledRequest(i, false);
                                if (z) {
                                    expandBuffer();
                                }
                                symbol15 = BufferedChannelKt.FAILED;
                                return symbol15;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            if (j < (sendersAndCloseStatus$FU.get(this) & 1152921504606846975L)) {
                symbol = BufferedChannelKt.POISONED;
                if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, symbol)) {
                    expandBuffer();
                    symbol2 = BufferedChannelKt.FAILED;
                    return symbol2;
                }
            } else {
                if (obj == null) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    return symbol3;
                }
                if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, obj)) {
                    expandBuffer();
                    symbol4 = BufferedChannelKt.SUSPEND;
                    return symbol4;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int updateCellSend(ChannelSegment channelSegment, int i, Object obj, long j, Object obj2, boolean z) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        channelSegment.storeElement$kotlinx_coroutines_core(i, obj);
        if (z) {
            return updateCellSendSlow(channelSegment, i, obj, j, obj2, z);
        }
        Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
        if (state$kotlinx_coroutines_core == null) {
            if (bufferOrRendezvousSend(j)) {
                if (channelSegment.casState$kotlinx_coroutines_core(i, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (channelSegment.casState$kotlinx_coroutines_core(i, null, obj2)) {
                    return 2;
                }
            }
        } else if (state$kotlinx_coroutines_core instanceof Waiter) {
            channelSegment.cleanElement$kotlinx_coroutines_core(i);
            if (tryResumeReceiver(state$kotlinx_coroutines_core, obj)) {
                symbol3 = BufferedChannelKt.DONE_RCV;
                channelSegment.setState$kotlinx_coroutines_core(i, symbol3);
                onReceiveDequeued();
                return 0;
            }
            symbol = BufferedChannelKt.INTERRUPTED_RCV;
            Object andSetState$kotlinx_coroutines_core = channelSegment.getAndSetState$kotlinx_coroutines_core(i, symbol);
            symbol2 = BufferedChannelKt.INTERRUPTED_RCV;
            if (andSetState$kotlinx_coroutines_core != symbol2) {
                channelSegment.onCancelledRequest(i, true);
            }
            return 5;
        }
        return updateCellSendSlow(channelSegment, i, obj, j, obj2, z);
    }

    private final int updateCellSendSlow(ChannelSegment channelSegment, int i, Object obj, long j, Object obj2, boolean z) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        while (true) {
            Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
            if (state$kotlinx_coroutines_core != null) {
                symbol2 = BufferedChannelKt.IN_BUFFER;
                if (state$kotlinx_coroutines_core != symbol2) {
                    symbol3 = BufferedChannelKt.INTERRUPTED_RCV;
                    if (state$kotlinx_coroutines_core == symbol3) {
                        channelSegment.cleanElement$kotlinx_coroutines_core(i);
                        return 5;
                    }
                    symbol4 = BufferedChannelKt.POISONED;
                    if (state$kotlinx_coroutines_core == symbol4) {
                        channelSegment.cleanElement$kotlinx_coroutines_core(i);
                        return 5;
                    }
                    Symbol channel_closed = BufferedChannelKt.getCHANNEL_CLOSED();
                    channelSegment.cleanElement$kotlinx_coroutines_core(i);
                    if (state$kotlinx_coroutines_core == channel_closed) {
                        completeCloseOrCancel();
                        return 4;
                    }
                    if (state$kotlinx_coroutines_core instanceof WaiterEB) {
                        state$kotlinx_coroutines_core = ((WaiterEB) state$kotlinx_coroutines_core).waiter;
                    }
                    if (tryResumeReceiver(state$kotlinx_coroutines_core, obj)) {
                        symbol7 = BufferedChannelKt.DONE_RCV;
                        channelSegment.setState$kotlinx_coroutines_core(i, symbol7);
                        onReceiveDequeued();
                        return 0;
                    }
                    symbol5 = BufferedChannelKt.INTERRUPTED_RCV;
                    Object andSetState$kotlinx_coroutines_core = channelSegment.getAndSetState$kotlinx_coroutines_core(i, symbol5);
                    symbol6 = BufferedChannelKt.INTERRUPTED_RCV;
                    if (andSetState$kotlinx_coroutines_core != symbol6) {
                        channelSegment.onCancelledRequest(i, true);
                    }
                    return 5;
                }
                if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (!bufferOrRendezvousSend(j) || z) {
                if (z) {
                    symbol = BufferedChannelKt.INTERRUPTED_SEND;
                    if (channelSegment.casState$kotlinx_coroutines_core(i, null, symbol)) {
                        channelSegment.onCancelledRequest(i, false);
                        return 4;
                    }
                } else {
                    if (obj2 == null) {
                        return 3;
                    }
                    if (channelSegment.casState$kotlinx_coroutines_core(i, null, obj2)) {
                        return 2;
                    }
                }
            } else if (channelSegment.casState$kotlinx_coroutines_core(i, null, BufferedChannelKt.BUFFERED)) {
                return 1;
            }
        }
    }

    private final void updateReceiversCounterIfLower(long j) {
        long j2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = receivers$FU;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            if (j2 >= j) {
                return;
            }
        } while (!receivers$FU.compareAndSet(this, j2, j));
    }

    private final void updateSendersCounterIfLower(long j) {
        long j2;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater atomicLongFieldUpdater = sendersAndCloseStatus$FU;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            long j3 = 1152921504606846975L & j2;
            if (j3 >= j) {
                return;
            } else {
                constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(j3, (int) (j2 >> 60));
            }
        } while (!sendersAndCloseStatus$FU.compareAndSet(this, j2, constructSendersAndCloseStatus));
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cancellationException) {
        cancelImpl$kotlinx_coroutines_core(cancellationException);
    }

    public boolean cancelImpl$kotlinx_coroutines_core(Throwable th) {
        if (th == null) {
            th = new CancellationException("Channel was cancelled");
        }
        return closeOrCancelImpl(th, true);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable th) {
        return closeOrCancelImpl(th, false);
    }

    protected boolean closeOrCancelImpl(Throwable th, boolean z) {
        Symbol symbol;
        if (z) {
            markCancellationStarted();
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _closeCause$FU;
        symbol = BufferedChannelKt.NO_CLOSE_CAUSE;
        boolean m = AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, symbol, th);
        if (z) {
            markCancelled();
        } else {
            markClosed();
        }
        completeCloseOrCancel();
        onClosedIdempotent();
        if (m) {
            invokeCloseHandler();
        }
        return m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long j) {
        Symbol symbol;
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        ChannelSegment channelSegment = (ChannelSegment) receiveSegment$FU.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = receivers$FU;
            long j2 = atomicLongFieldUpdater.get(this);
            if (j < Math.max(this.capacity + j2, getBufferEndCounter())) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j2, j2 + 1)) {
                int i = BufferedChannelKt.SEGMENT_SIZE;
                long j3 = j2 / i;
                int i2 = (int) (j2 % i);
                if (channelSegment.id != j3) {
                    ChannelSegment findSegmentReceive = findSegmentReceive(j3, channelSegment);
                    if (findSegmentReceive == null) {
                        continue;
                    } else {
                        channelSegment = findSegmentReceive;
                    }
                }
                Object updateCellReceive = updateCellReceive(channelSegment, i2, j2, null);
                symbol = BufferedChannelKt.FAILED;
                if (updateCellReceive != symbol) {
                    channelSegment.cleanPrev();
                    Function1 function1 = this.onUndeliveredElement;
                    if (function1 != null && (callUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, updateCellReceive, null, 2, null)) != null) {
                        throw callUndeliveredElementCatchingException$default;
                    }
                } else if (j2 < getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
            }
        }
    }

    protected final Throwable getCloseCause() {
        return (Throwable) _closeCause$FU.get(this);
    }

    public final long getReceiversCounter$kotlinx_coroutines_core() {
        return receivers$FU.get(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Throwable getSendException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedSendChannelException("Channel was closed") : closeCause;
    }

    public final long getSendersCounter$kotlinx_coroutines_core() {
        return sendersAndCloseStatus$FU.get(this) & 1152921504606846975L;
    }

    public final boolean hasElements$kotlinx_coroutines_core() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = receiveSegment$FU;
            ChannelSegment channelSegment = (ChannelSegment) atomicReferenceFieldUpdater.get(this);
            long receiversCounter$kotlinx_coroutines_core = getReceiversCounter$kotlinx_coroutines_core();
            if (getSendersCounter$kotlinx_coroutines_core() <= receiversCounter$kotlinx_coroutines_core) {
                return false;
            }
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j = receiversCounter$kotlinx_coroutines_core / i;
            if (channelSegment.id == j || (channelSegment = findSegmentReceive(j, channelSegment)) != null) {
                channelSegment.cleanPrev();
                if (isCellNonEmpty(channelSegment, (int) (receiversCounter$kotlinx_coroutines_core % i), receiversCounter$kotlinx_coroutines_core)) {
                    return true;
                }
                receivers$FU.compareAndSet(this, receiversCounter$kotlinx_coroutines_core, receiversCounter$kotlinx_coroutines_core + 1);
            } else if (((ChannelSegment) atomicReferenceFieldUpdater.get(this)).id < j) {
                return false;
            }
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1 function1) {
        Symbol symbol;
        Symbol symbol2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Symbol symbol3;
        Symbol symbol4;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = closeHandler$FU;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater2, this, null, function1)) {
            return;
        }
        do {
            Object obj = atomicReferenceFieldUpdater2.get(this);
            symbol = BufferedChannelKt.CLOSE_HANDLER_CLOSED;
            if (obj != symbol) {
                symbol2 = BufferedChannelKt.CLOSE_HANDLER_INVOKED;
                if (obj == symbol2) {
                    throw new IllegalStateException("Another handler was already registered and successfully invoked".toString());
                }
                throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
            }
            atomicReferenceFieldUpdater = closeHandler$FU;
            symbol3 = BufferedChannelKt.CLOSE_HANDLER_CLOSED;
            symbol4 = BufferedChannelKt.CLOSE_HANDLER_INVOKED;
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, symbol3, symbol4));
        function1.invoke(getCloseCause());
    }

    public boolean isClosedForReceive() {
        return isClosedForReceive0(sendersAndCloseStatus$FU.get(this));
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return isClosedForSend0(sendersAndCloseStatus$FU.get(this));
    }

    protected boolean isConflatedDropOldest() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator iterator() {
        return new BufferedChannelIterator();
    }

    protected void onClosedIdempotent() {
    }

    protected void onReceiveDequeued() {
    }

    protected void onReceiveEnqueued() {
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU, reason: not valid java name */
    public Object mo54receiveCatchingJP2dKIU(Continuation continuation) {
        return m52receiveCatchingJP2dKIU$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object send(Object obj, Continuation continuation) {
        return send$suspendImpl(this, obj, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:97:0x01b0, code lost:
    
        r3 = (kotlinx.coroutines.channels.ChannelSegment) r3.getNext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01b7, code lost:
    
        if (r3 != null) goto L91;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.toString():java.lang.String");
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk, reason: not valid java name */
    public Object mo55tryReceivePtdJZtk() {
        Object obj;
        ChannelSegment channelSegment;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        long j = receivers$FU.get(this);
        long j2 = sendersAndCloseStatus$FU.get(this);
        if (isClosedForReceive0(j2)) {
            return ChannelResult.Companion.m67closedJP2dKIU(getCloseCause());
        }
        if (j >= (j2 & 1152921504606846975L)) {
            return ChannelResult.Companion.m68failurePtdJZtk();
        }
        obj = BufferedChannelKt.INTERRUPTED_RCV;
        ChannelSegment channelSegment2 = (ChannelSegment) receiveSegment$FU.get(this);
        while (!isClosedForReceive()) {
            long andIncrement = receivers$FU.getAndIncrement(this);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j3 = andIncrement / i;
            int i2 = (int) (andIncrement % i);
            if (channelSegment2.id != j3) {
                ChannelSegment findSegmentReceive = findSegmentReceive(j3, channelSegment2);
                if (findSegmentReceive == null) {
                    continue;
                } else {
                    channelSegment = findSegmentReceive;
                }
            } else {
                channelSegment = channelSegment2;
            }
            Object updateCellReceive = updateCellReceive(channelSegment, i2, andIncrement, obj);
            symbol = BufferedChannelKt.SUSPEND;
            if (updateCellReceive == symbol) {
                Waiter waiter = obj instanceof Waiter ? (Waiter) obj : null;
                if (waiter != null) {
                    prepareReceiverForSuspension(waiter, channelSegment, i2);
                }
                waitExpandBufferCompletion$kotlinx_coroutines_core(andIncrement);
                channelSegment.onSlotCleaned();
                return ChannelResult.Companion.m68failurePtdJZtk();
            }
            symbol2 = BufferedChannelKt.FAILED;
            if (updateCellReceive != symbol2) {
                symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                if (updateCellReceive == symbol3) {
                    throw new IllegalStateException("unexpected".toString());
                }
                channelSegment.cleanPrev();
                return ChannelResult.Companion.m69successJP2dKIU(updateCellReceive);
            }
            if (andIncrement < getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment.cleanPrev();
            }
            channelSegment2 = channelSegment;
        }
        return ChannelResult.Companion.m67closedJP2dKIU(getCloseCause());
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00bb, code lost:
    
        return kotlinx.coroutines.channels.ChannelResult.Companion.m69successJP2dKIU(kotlin.Unit.INSTANCE);
     */
    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object mo56trySendJP2dKIU(java.lang.Object r15) {
        /*
            r14 = this;
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.sendersAndCloseStatus$FU
            long r0 = r0.get(r14)
            boolean r0 = r14.shouldSendSuspend(r0)
            if (r0 == 0) goto L13
            kotlinx.coroutines.channels.ChannelResult$Companion r14 = kotlinx.coroutines.channels.ChannelResult.Companion
            java.lang.Object r14 = r14.m68failurePtdJZtk()
            return r14
        L13:
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.BufferedChannelKt.access$getINTERRUPTED_SEND$p()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = access$getSendSegment$FU$p()
            java.lang.Object r0 = r0.get(r14)
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
        L21:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = access$getSendersAndCloseStatus$FU$p()
            long r1 = r1.getAndIncrement(r14)
            r3 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r9 = r1 & r3
            boolean r11 = access$isClosedForSend0(r14, r1)
            int r1 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r2 = (long) r1
            long r2 = r9 / r2
            long r4 = (long) r1
            long r4 = r9 % r4
            int r12 = (int) r4
            long r4 = r0.id
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L4e
            kotlinx.coroutines.channels.ChannelSegment r1 = access$findSegmentSend(r14, r2, r0)
            if (r1 != 0) goto L4c
            if (r11 == 0) goto L21
            goto L8e
        L4c:
            r13 = r1
            goto L4f
        L4e:
            r13 = r0
        L4f:
            r0 = r14
            r1 = r13
            r2 = r12
            r3 = r15
            r4 = r9
            r6 = r8
            r7 = r11
            int r0 = access$updateCellSend(r0, r1, r2, r3, r4, r6, r7)
            if (r0 == 0) goto Lb0
            r1 = 1
            if (r0 == r1) goto Lb3
            r1 = 2
            if (r0 == r1) goto L89
            r1 = 3
            if (r0 == r1) goto L7d
            r1 = 4
            if (r0 == r1) goto L71
            r1 = 5
            if (r0 == r1) goto L6c
            goto L6f
        L6c:
            r13.cleanPrev()
        L6f:
            r0 = r13
            goto L21
        L71:
            long r0 = r14.getReceiversCounter$kotlinx_coroutines_core()
            int r15 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r15 >= 0) goto L8e
            r13.cleanPrev()
            goto L8e
        L7d:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "unexpected"
            java.lang.String r15 = r15.toString()
            r14.<init>(r15)
            throw r14
        L89:
            if (r11 == 0) goto L99
            r13.onSlotCleaned()
        L8e:
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.Companion
            java.lang.Throwable r14 = r14.getSendException()
            java.lang.Object r14 = r15.m67closedJP2dKIU(r14)
            goto Lbb
        L99:
            boolean r15 = r8 instanceof kotlinx.coroutines.Waiter
            if (r15 == 0) goto La0
            kotlinx.coroutines.Waiter r8 = (kotlinx.coroutines.Waiter) r8
            goto La1
        La0:
            r8 = 0
        La1:
            if (r8 == 0) goto La6
            access$prepareSenderForSuspension(r14, r8, r13, r12)
        La6:
            r13.onSlotCleaned()
            kotlinx.coroutines.channels.ChannelResult$Companion r14 = kotlinx.coroutines.channels.ChannelResult.Companion
            java.lang.Object r14 = r14.m68failurePtdJZtk()
            goto Lbb
        Lb0:
            r13.cleanPrev()
        Lb3:
            kotlinx.coroutines.channels.ChannelResult$Companion r14 = kotlinx.coroutines.channels.ChannelResult.Companion
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            java.lang.Object r14 = r14.m69successJP2dKIU(r15)
        Lbb:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.mo56trySendJP2dKIU(java.lang.Object):java.lang.Object");
    }

    public final void waitExpandBufferCompletion$kotlinx_coroutines_core(long j) {
        int i;
        long j2;
        long constructEBCompletedAndPauseFlag;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long constructEBCompletedAndPauseFlag2;
        long j3;
        long constructEBCompletedAndPauseFlag3;
        if (isRendezvousOrUnlimited()) {
            return;
        }
        do {
        } while (getBufferEndCounter() <= j);
        i = BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS;
        for (int i2 = 0; i2 < i; i2++) {
            long bufferEndCounter = getBufferEndCounter();
            if (bufferEndCounter == (4611686018427387903L & completedExpandBuffersAndPauseFlag$FU.get(this)) && bufferEndCounter == getBufferEndCounter()) {
                return;
            }
        }
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = completedExpandBuffersAndPauseFlag$FU;
        do {
            j2 = atomicLongFieldUpdater2.get(this);
            constructEBCompletedAndPauseFlag = BufferedChannelKt.constructEBCompletedAndPauseFlag(j2 & 4611686018427387903L, true);
        } while (!atomicLongFieldUpdater2.compareAndSet(this, j2, constructEBCompletedAndPauseFlag));
        while (true) {
            long bufferEndCounter2 = getBufferEndCounter();
            atomicLongFieldUpdater = completedExpandBuffersAndPauseFlag$FU;
            long j4 = atomicLongFieldUpdater.get(this);
            long j5 = j4 & 4611686018427387903L;
            boolean z = (4611686018427387904L & j4) != 0;
            if (bufferEndCounter2 == j5 && bufferEndCounter2 == getBufferEndCounter()) {
                break;
            } else if (!z) {
                constructEBCompletedAndPauseFlag2 = BufferedChannelKt.constructEBCompletedAndPauseFlag(j5, true);
                atomicLongFieldUpdater.compareAndSet(this, j4, constructEBCompletedAndPauseFlag2);
            }
        }
        do {
            j3 = atomicLongFieldUpdater.get(this);
            constructEBCompletedAndPauseFlag3 = BufferedChannelKt.constructEBCompletedAndPauseFlag(j3 & 4611686018427387903L, false);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j3, constructEBCompletedAndPauseFlag3));
    }
}
