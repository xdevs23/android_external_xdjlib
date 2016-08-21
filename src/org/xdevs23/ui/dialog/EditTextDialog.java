package org.xdevs23.ui.dialog;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.xdevs23.android.app.XquidCompatActivity;
import org.xdevs23.android.widget.XquidRelativeLayout;
import org.xdevs23.general.ExtendedAndroidClass;
import org.xdevs23.ui.dialog.templates.DismissDialogButton;
import org.xdevs23.ui.utils.DpUtil;

import org.xdevs23.R;

public class EditTextDialog extends ExtendedAndroidClass {

    private DialogInterface.OnClickListener onClickListener;

    private EditText mEditText = null;

    private String mTitle = "", mDefaultText = "", mDescText = "";

    private Activity mActivity;

    public EditTextDialog(Context context) {
        super(context);
        init();
    }

    public EditTextDialog(Context context, Activity activity, String title, String defaultText) {
        this(context, activity, title, defaultText, "");
    }


    public EditTextDialog(Context context, Activity activity, String title,
                          String defaultText, String descText) {
        super(context);
        setContext(activity);
        setPrivateParams(title, defaultText, descText);
        setPrivateActivity(activity);
        init();
    }

    private void setPrivateParams(String title, String defaultText, String descText) {
        mTitle = title; mDefaultText = defaultText; mDescText = descText;
    }

    private void init() {
        mEditText = new EditText(getContext());
        mEditText.setSingleLine();
        if(!mTitle.isEmpty()) mEditText.setHint(mTitle);
        if(!mTitle.isEmpty()) mEditText.getEditableText().insert(0, mDefaultText);
        mEditText.setTextColor(Color.BLACK);

        onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    }

    public EditTextDialog setOnClickListener(DialogInterface.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public EditTextDialog setPrivateActivity(Activity activity) {
        this.mActivity = activity;
        return this;
    }

    public EditTextDialog setPrivateActivity(XquidCompatActivity activity) {
        this.mActivity = activity;
        return this;
    }

    public EditText getEditText() {
        return mEditText;
    }

    public String getEnteredText() {
        return mEditText.getEditableText().toString();
    }

    public void showDialog() {
        XquidRelativeLayout dialogContent = new XquidRelativeLayout(getContext());
        final TextView descTextView = new TextView(getContext());
        descTextView.setText(mDescText);
        dialogContent.addView(descTextView);
        dialogContent.addView(mEditText);
        XquidRelativeLayout.LayoutParams lp = new XquidRelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (mDescText.isEmpty() ? 0 : DpUtil.dp2px(getContext(), 140))
        );
        lp.setMarginsDp(8, 8, 8, 8, getContext());
        dialogContent.setLayoutParams(lp);
        dialogContent.setMinimumHeight(lp.height);
        XquidRelativeLayout.LayoutParams lpe = new XquidRelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        mEditText.setLayoutParams(lpe);
        // Make sure mEditText is below descTextView
        descTextView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getEditText().setTranslationY((mDescText.isEmpty() ? 0 :
                        descTextView.getHeight() + DpUtil.dp2px(getContext(), 2)));
                if( Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
                    descTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder
                .setCancelable(true)
                .setNegativeButton(android.R.string.cancel, new DismissDialogButton())
                .setPositiveButton(android.R.string.ok, onClickListener)
                .setTitle(mTitle)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mEditText.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getContext()
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
                    }
                })
                .setView(dialogContent).create().show();

        mEditText.requestFocus();
        InputMethodManager imm =
                (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

}