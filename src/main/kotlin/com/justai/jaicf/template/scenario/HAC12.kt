package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.event.event
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.yandexalice.activator.alice
import com.justai.jaicf.channel.yandexalice.model.AliceEvent
import com.justai.jaicf.channel.yandexalice.alice

val HAC12 = Scenario {

    state("main") {
        activators {
            event(AliceEvent.START)
        }
        action {
            reactions.say("!")
        }
    }

    state("weather") {
        activators {
            intent("WEATHER")
        }
        action {
            activator.alice?.run {
                val where = slots["where"]
                reactions.say("Определяю погоду в $where")
                reactions.alice?.endSession()
            }
        }
    }

    fallback {
        reactions.say("Не понял(-а)")
    }
}