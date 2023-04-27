package com.vaadin.scoregoatvaadin.service;

import com.vaadin.flow.component.notification.NotificationVariant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NotificationSelection {
    public NotificationVariant selectVariant(String type) {
        if (type.equals("success")) {
            return NotificationVariant.LUMO_SUCCESS;
        } else {
            if (type.equals("error")) {
                return NotificationVariant.LUMO_ERROR;
            } else
                return NotificationVariant.LUMO_PRIMARY;
        }
    }
}
