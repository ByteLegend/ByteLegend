package com.bytelegend.client.app.ui

import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import react.RBuilder
import react.State
import react.dom.ReactHTML.p

class HistoryModal : GameUIComponent<GameProps, State>() {
    override fun RBuilder.render() {
        BootstrapModalHeader {
            attrs.closeButton = true
            BootstrapModalTitle {
                +i("UnfinishedTitle")
            }
        }
        BootstrapModalBody {
            p {
                unsafeSpan(i("UnfinishedText2"))
            }
        }
    }
}
