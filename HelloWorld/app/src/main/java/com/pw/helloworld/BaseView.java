package com.pw.helloworld;

import org.jetbrains.annotations.NotNull;

public interface BaseView<T> {

    void setPresenter(@NotNull T presenter);
}
