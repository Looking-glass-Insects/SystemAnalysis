package view;

import controller.*;
import event.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Created by hy on 2017/10/14.
 */
public class FunctionWindow implements IPublisher, OnRoleChanged {
    private FunctionController controller;


    private FunctionWindow() {
        workNameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                WorkInfoEvent event = new WorkInfoEvent();
                event.setOpId(Config.OP.WRITE);
                sendEvent(event, new CallBack());
            }
        });
        contentText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                WorkInfoEvent event = new WorkInfoEvent();
                event.setOpId(Config.OP.WRITE);
                sendEvent(event, new CallBack());
            }
        });
        assessmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssessmentEvent event = new AssessmentEvent();
                event.setOpId(Config.OP.WRITE);
                sendEvent(event, new CallBack());
            }
        });
        gradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GradeEvent event = new GradeEvent();
                event.setOpId(Config.OP.WRITE);
                sendEvent(event, new CallBack());
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubmitEvent event = new SubmitEvent();
                event.setOpId(Config.OP.WRITE);
                sendEvent(event, new CallBack());
            }
        });
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CountEvent event = new CountEvent();
                event.setOpId(Config.OP.WRITE);
                sendEvent(event, new CallBack());
            }
        });
    }

    static class CallBack implements EventCallBack {
        @Override
        public void isAccept(boolean isAccept) {
            if (!isAccept) {
                JOptionPane.showConfirmDialog(null, "权限不足", "提示", JOptionPane.YES_NO_OPTION);
            }
        }
    }


    public static FunctionWindow getFunctionWindow() {
        FunctionWindow window = new FunctionWindow();
        window.registerSubscriber(FunctionController.getFunctionController());
        window.init();
        Attachment.getAttachment().addListener(window);
        return window;
    }

    @Override
    public void registerSubscriber(ISubscriber subscriber) {
        this.controller = (FunctionController) subscriber;
    }

    @Override
    public void sendEvent(IEvent event, EventCallBack callBack) {
        controller.getEvent(event, callBack);
    }

    public void init() {
        sendReadableEvent();
    }

    private void sendReadableEvent() {
        AssessmentEvent assessmentEvent = new AssessmentEvent();
        assessmentEvent.setOpId(Config.OP.READ);
        sendEvent(assessmentEvent, new EventCallBack() {
            @Override
            public void isAccept(boolean isAccept) {
                assessmentButton.setVisible(isAccept);
            }
        });

        CountEvent countEvent = new CountEvent();
        countEvent.setOpId(Config.OP.READ);
        sendEvent(countEvent, new EventCallBack() {
            @Override
            public void isAccept(boolean isAccept) {

                countButton.setVisible(isAccept);

            }
        });


        GradeEvent gradeEvent = new GradeEvent();
        gradeEvent.setOpId(Config.OP.READ);
        sendEvent(gradeEvent, new EventCallBack() {
            @Override
            public void isAccept(boolean isAccept) {

                gradeButton.setVisible(isAccept);

            }
        });


        SubmitEvent submitEvent = new SubmitEvent();
        submitEvent.setOpId(Config.OP.READ);
        sendEvent(submitEvent, new EventCallBack() {
            @Override
            public void isAccept(boolean isAccept) {

                submitButton.setVisible(isAccept);

            }
        });

        WorkInfoEvent workInfoEvent = new WorkInfoEvent();
        workInfoEvent.setOpId(Config.OP.READ);
        sendEvent(workInfoEvent, new EventCallBack() {
            @Override
            public void isAccept(boolean isAccept) {

                workNameText.setVisible(isAccept);
                contentText.setVisible(isAccept);

            }
        });
    }

    @Override
    public void onRoleChanged() {
        sendReadableEvent();
    }

    public JPanel getPanel() {
        return panel1;
    }


    private JPanel panel1;
    private JTextArea contentText;
    private JButton assessmentButton;
    private JButton gradeButton;
    private JButton submitButton;
    private JButton countButton;
    private JTextField workNameText;


}
