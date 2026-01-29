package com.example.languageapp.comp.presentation.widget

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.Spacer
import androidx.glance.layout.width
import androidx.glance.layout.size
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.text.FontWeight
import androidx.glance.unit.dp
import androidx.glance.unit.sp
import androidx.glance.image.Image
import androidx.glance.image.ImageProvider
import androidx.glance.material3.Card
import androidx.glance.material3.CardDefaults
import androidx.glance.material3.RoundedCornerShape
import androidx.glance.unit.ColorProvider
import com.example.languageapp.R
import com.example.languageapp.comp.presentation.mainScreen.components.WidgetTopUser

object LeaderBoardWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        val topUsers = listOf(
            WidgetTopUser(number = "5.", name = "Vincent van Gogh", points = 12, avatar = R.drawable.vangogh, isSelected = false),
            WidgetTopUser(number = "6.", name = "Vincent van Gogh", points = 12, avatar = R.drawable.vangogh, isSelected = false),
            WidgetTopUser(number = "7.", name = "You", points = 12, avatar = R.drawable.widgetavatar, isSelected = true),
            WidgetTopUser(number = "8.", name = "Vincent van Gogh", points = 12, avatar = R.drawable.vangogh, isSelected = false),
        )

        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(ColorProvider(R.color.widget_background)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your place is 7! Awesome!",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )

            topUsers.forEach { user ->
                TopWidgetUserItem(user)
            }
        }
    }
}

@Composable
fun TopWidgetUserItem(user: WidgetTopUser) {
    Card(
        modifier = GlanceModifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorProvider(R.color.widget_card_bg),
            contentColor = ColorProvider(R.color.widget_text)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = GlanceModifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                provider = ImageProvider(user.avatar),
                contentDescription = "User avatar",
                modifier = GlanceModifier.size(36.dp)
            )
            Spacer(modifier = GlanceModifier.width(12.dp))
            Text(
                text = user.name,
                modifier = GlanceModifier.weight(1f),
                style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp)
            )
            Text(
                text = "${user.points} pts",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)
            )
        }
    }
}

class SimpleLeaderboardWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = LeaderBoardWidget
}