package aldana.gilberto.chatbot.utils

import aldana.gilberto.chatbot.utils.Constans.OPEN_GOOGLE
import aldana.gilberto.chatbot.utils.Constans.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {
    fun basicResponses(_message: String):String{
        val random = (0..2).random()
        val message = _message.toLowerCase()

        return when {
            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"
                } catch (e: Exception) {
                    "Sorry, I can't solve that"
                }
            }

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Boungiorno"
                    else -> "error"
                }
            }

            //How are you
            message.contains("time") && message.contains("?")->{
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")->{
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")->{
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else->{
                when(random){
                    0 -> "I don't understand"
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}