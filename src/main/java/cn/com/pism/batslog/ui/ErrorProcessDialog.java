package cn.com.pism.batslog.ui;

import cn.com.pism.batslog.model.BslErrorMod;
import cn.com.pism.batslog.ui.component.SqlErrorProcessPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.DimensionService;
import com.intellij.ui.JBSplitter;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * @author PerccyKing
 * @version 0.0.1
 * @date 2021/10/30 下午 06:03
 * @since 0.0.1
 */
public class ErrorProcessDialog extends DialogWrapper {

    private JPanel root;

    private SqlEditorPanel sqlEditorPanel;

    private Project project;

    private static final String ERROR_PROCESS_DIALOG_SIZE_KEY = "#cn.com.pism.batslog.ui.ErrorProcessDialog";


    public ErrorProcessDialog(@Nullable Project project) {
        super(project);
        init();
        this.project = project;
        final Dimension size = DimensionService.getInstance().getSize(ERROR_PROCESS_DIALOG_SIZE_KEY);
        if (size != null) {
            root.setPreferredSize(size);
        }
        setTitle("错误详情");
    }

    public ErrorProcessDialog(Project project, BslErrorMod bslErrorMod) {
        this(project);
        final SqlErrorProcessPanel processPanel = new SqlErrorProcessPanel(project, bslErrorMod);
        final SqlEditorPanel sqlEditorPanel = new SqlEditorPanel(project, bslErrorMod.getSql());
        final JBSplitter jbSplitter = new JBSplitter(false, 0.7f);
        jbSplitter.setFirstComponent(processPanel.getRoot());
        jbSplitter.setSecondComponent(sqlEditorPanel.getRoot());
        root.add(jbSplitter);
        this.sqlEditorPanel = sqlEditorPanel;
        show();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return root;
    }


    @Override
    protected void doOKAction() {
        final Dimension size = getSize();
        if (size != null) {
            DimensionService.getInstance().setSize(ERROR_PROCESS_DIALOG_SIZE_KEY, size);
        }
        super.doOKAction();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean isShowing() {
        sqlEditorPanel.reformatCode();
        return super.isShowing();
    }

}
