package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class GuidelineReference extends WidgetRun {
    public GuidelineReference(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.horizontalRun.clear();
        constraintWidget.verticalRun.clear();
        this.orientation = ((Guideline) constraintWidget).getOrientation();
    }

    private void addDependency(DependencyNode dependencyNode) {
        this.start.dependencies.add(dependencyNode);
        dependencyNode.targets.add(this.start);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void apply() {
        DependencyNode dependencyNode;
        WidgetRun widgetRun;
        DependencyNode dependencyNode2;
        Guideline guideline = (Guideline) this.widget;
        int relativeBegin = guideline.getRelativeBegin();
        int relativeEnd = guideline.getRelativeEnd();
        guideline.getRelativePercent();
        if (guideline.getOrientation() == 1) {
            DependencyNode dependencyNode3 = this.start;
            if (relativeBegin != -1) {
                dependencyNode3.targets.add(this.widget.mParent.horizontalRun.start);
                this.widget.mParent.horizontalRun.start.dependencies.add(this.start);
                dependencyNode2 = this.start;
            } else if (relativeEnd != -1) {
                dependencyNode3.targets.add(this.widget.mParent.horizontalRun.end);
                this.widget.mParent.horizontalRun.end.dependencies.add(this.start);
                dependencyNode2 = this.start;
                relativeBegin = -relativeEnd;
            } else {
                dependencyNode3.delegateToWidgetRun = true;
                dependencyNode3.targets.add(this.widget.mParent.horizontalRun.end);
                this.widget.mParent.horizontalRun.end.dependencies.add(this.start);
                addDependency(this.widget.horizontalRun.start);
                widgetRun = this.widget.horizontalRun;
            }
            dependencyNode2.margin = relativeBegin;
            addDependency(this.widget.horizontalRun.start);
            widgetRun = this.widget.horizontalRun;
        } else {
            DependencyNode dependencyNode4 = this.start;
            if (relativeBegin != -1) {
                dependencyNode4.targets.add(this.widget.mParent.verticalRun.start);
                this.widget.mParent.verticalRun.start.dependencies.add(this.start);
                dependencyNode = this.start;
            } else if (relativeEnd != -1) {
                dependencyNode4.targets.add(this.widget.mParent.verticalRun.end);
                this.widget.mParent.verticalRun.end.dependencies.add(this.start);
                dependencyNode = this.start;
                relativeBegin = -relativeEnd;
            } else {
                dependencyNode4.delegateToWidgetRun = true;
                dependencyNode4.targets.add(this.widget.mParent.verticalRun.end);
                this.widget.mParent.verticalRun.end.dependencies.add(this.start);
                addDependency(this.widget.verticalRun.start);
                widgetRun = this.widget.verticalRun;
            }
            dependencyNode.margin = relativeBegin;
            addDependency(this.widget.verticalRun.start);
            widgetRun = this.widget.verticalRun;
        }
        addDependency(widgetRun.end);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if (((Guideline) this.widget).getOrientation() == 1) {
            this.widget.setX(this.start.value);
        } else {
            this.widget.setY(this.start.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.start.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.readyToSolve && !dependencyNode.resolved) {
            this.start.resolve((int) ((((DependencyNode) dependencyNode.targets.get(0)).value * ((Guideline) this.widget).getRelativePercent()) + 0.5f));
        }
    }
}
