package com.example.composeplayground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun BalloonTextTest() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Android表示サンプル",
            modifier = Modifier.padding(4.dp))
        BalloonText("Composeから\nこんにちは!",
            modifier = Modifier.padding(4.dp))
        BalloonText("逆向きの吹き出しです", mirrored = true,
            modifier = Modifier.padding(4.dp))
        BalloonText("長いテキストの表示例です。テキストの幅と高さに合わせて、吹き出しの大きさも自動的に連動して表示されます。",
            modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun BalloonText(
    text: String,
    balloonColor: Color = Color(red = 109, green = 230, blue = 123),
    mirrored: Boolean = false,
    modifier: Modifier = Modifier
) {
    if (mirrored) {
        YourBalloonText(text, balloonColor, modifier)
    } else {
        MyBalloonText(text, balloonColor, modifier)
    }
}

@Composable
fun MyBalloonText(
    text: String,
    balloonColor: Color = Color.Green,
    modifier: Modifier = Modifier
) {
    val cornerRadius = with(LocalDensity.current) { 8.dp.toPx() }
    val tailSize = Size(cornerRadius / 2, cornerRadius / 2)
    val tailWidthDp = with(LocalDensity.current) { tailSize.width.toDp() }

    val balloonShape = GenericShape { size, _ ->
        val shapeRect = Rect(Offset(0f, 0f), Size(size.width, size.height))
        val arcSize = Size(cornerRadius * 2, cornerRadius * 2)
        var arcRect: Rect
        var x: Float
        var y: Float
        var controlX: Float
        var controlY: Float

        // 時計回りに描いていく

        // 左上角丸
        x = shapeRect.left
        y = shapeRect.top
        arcRect = Rect(Offset(x, y), arcSize)
        arcTo(
            rect = arcRect,
            startAngleDegrees = 180f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )

        // 右上角丸
        x = shapeRect.right - (cornerRadius * 2) - tailSize.width
        y = shapeRect.top
        arcRect = Rect(Offset(x, y), arcSize)
        arcTo(arcRect, 270f, 45f, false)

        // しっぽ上部
        x = shapeRect.right
        y = shapeRect.top
        controlX = shapeRect.right - tailSize.width / 2
        controlY = shapeRect.top
        //lineTo(x, y)
        quadraticBezierTo(controlX, controlY, x, y)

        // しっぽ下部
        x = shapeRect.right - tailSize.width
        y = shapeRect.top + (cornerRadius / 2) + tailSize.height
        controlX = shapeRect.right - tailSize.width / 2
        controlY = shapeRect.top
        //lineTo(x, y)
        quadraticBezierTo(controlX, controlY, x, y)

        // 右下角丸
        x = shapeRect.right - tailSize.width - cornerRadius * 2
        y = shapeRect.bottom - (cornerRadius * 2)
        arcRect = Rect(Offset(x, y), arcSize)
        arcTo(arcRect, 0f, 90f, false)

        // 左下角丸
        x = shapeRect.left
        y = shapeRect.bottom - (cornerRadius * 2)
        arcRect = Rect(Offset(x, y), arcSize)
        arcTo(arcRect, 90f, 90f, false)
    }

    Text(
        text,
        modifier = modifier
            .background(balloonColor, shape = balloonShape)
            .padding(start = 4.dp, end = tailWidthDp + 2.dp)
            .padding(vertical = 1.dp)
    )
}

@Composable
fun YourBalloonText(
    text: String,
    balloonColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    val cornerRadius = with(LocalDensity.current) { 8.dp.toPx() }
    val tailSize = Size(cornerRadius / 2, cornerRadius / 2)
    val tailWidthDp = with(LocalDensity.current) { tailSize.width.toDp() }

    val BalloonShape = GenericShape { size, _ ->
        val shapeRect = Rect(Offset(0f, 0f), Size(size.width, size.height))
        val arcSize = Size(cornerRadius * 2, cornerRadius * 2)
        var arcRect: Rect
        var x: Float
        var y: Float
        var controlX: Float
        var controlY: Float

        // 反時計回りに描画していく

        // 左上角丸
        x = shapeRect.left + tailSize.width
        y = shapeRect.top
        arcRect = Rect(Offset(x, y), arcSize)
        arcTo(arcRect, 270f, -45f, false)

        // しっぽ上部
        x = shapeRect.left
        y = shapeRect.top
        controlX = shapeRect.left + tailSize.width / 2
        controlY = shapeRect.top
        //lineTo(x, y)
        quadraticBezierTo(controlX, controlY, x, y)

        // しっぽ下部
        x = shapeRect.left + tailSize.width
        y = shapeRect.top + (cornerRadius / 2) + tailSize.height
        controlX = shapeRect.left + tailSize.width / 2
        controlY = shapeRect.top
        //lineTo(x, y)
        quadraticBezierTo(controlX, controlY, x, y)

        // 左下角丸
        x = shapeRect.left + tailSize.width
        y = shapeRect.bottom - (cornerRadius * 2)
        arcRect = Rect(Offset(x, y), arcSize)
        arcTo(arcRect, 180f, -90f, false)

        // 右下角丸
        x = shapeRect.right - cornerRadius
        y = shapeRect.bottom - cornerRadius * 2
        arcRect = Rect(Offset(x - cornerRadius, y), arcSize)
        arcTo(arcRect, 90f, -90f, false)

        // 右上角丸
        x = shapeRect.right - cornerRadius * 2
        y = shapeRect.top
        arcRect = Rect(Offset(x, y), arcSize)
        arcTo(arcRect, 0f, -90f, false)
    }

    Text(
        text,
        modifier = modifier
            .background(balloonColor, shape = BalloonShape)
            .padding(start = tailWidthDp + 4.dp, end = 2.dp)
            .padding(vertical = 1.dp)
    )
}

//@Composable
//fun BackgroundSample() {
//    val myShape = GenericShape { size, _ ->
//        addRect(Rect(0f, 0f, size.width, size.height))
//    }
//
//    Text(
//        "背景サンプル",
//        modifier = Modifier.background(Color.Green, shape = myShape)
//    )
//}
//


//@Composable
//fun BalloonText(text: String, modifier: Modifier = Modifier) {
//    val padding = with(LocalDensity.current) { 1.dp.toPx() }
//    val cornerRadius = with(LocalDensity.current) { 6.dp.toPx() }
//    val tailSize = Size(cornerRadius / 2, cornerRadius / 2)
//    val tailWidthDp = with(LocalDensity.current) { tailSize.width.toDp() }
//
//    val balloonShape = GenericShape { size, _ ->
//        var x: Float
//        var y: Float
//        var controlX: Float
//        var controlY: Float
//        var arcRect: Rect
//        val drawRect = Rect(
//            Offset(0f + padding, 0f + padding),
//            Size(size.width - padding * 2, size.height - padding * 2)
//        )
//
//        // 左上角丸
//        arcRect = Rect(
//            Offset(drawRect.left + tailSize.width, drawRect.top),
//            Size(cornerRadius * 2, cornerRadius * 2)
//        )
//        arcTo(arcRect, 180f, 90f, false)
//
//        // 上辺
//        x = drawRect.right - cornerRadius
//        y = drawRect.top
//        lineTo(x, y)
//
//        // 右上角丸
//        arcRect = Rect(
//            Offset(x - cornerRadius, y),
//            Size(cornerRadius * 2, cornerRadius * 2)
//        )
//        arcTo(arcRect, 270f, 90f, false)
//
//        // 左辺
//        x = drawRect.right
//        y = drawRect.bottom - cornerRadius
//        lineTo(x, y)
//
//        // 右下角丸
//        arcRect = Rect(
//            Offset(x - cornerRadius * 2, y - cornerRadius),
//            Size(cornerRadius * 2, cornerRadius * 2)
//        )
//        arcTo(arcRect, 0f, 90f, false)
//
//        // 下辺
//        x = drawRect.left + cornerRadius + tailSize.width
//        y = drawRect.bottom
//        lineTo(x, y)
//
//        // 左下角丸
//        arcRect = Rect(
//            Offset(x - cornerRadius, y - cornerRadius * 2),
//            Size(cornerRadius * 2, cornerRadius * 2)
//        )
//        arcTo(arcRect, 90f, 45f, false)
//
//        // しっぽ下部
//        x = drawRect.left
//        y = drawRect.bottom
//        controlX = drawRect.left + tailSize.width / 2
//        controlY = drawRect.bottom
//        //lineTo(x, y)
//        quadraticBezierTo(controlX, controlY, x, y)
//
//        // しっぽ上部
//        x = drawRect.left + tailSize.width
//        y = drawRect.bottom - cornerRadius - tailSize.height
//        controlX = drawRect.left + tailSize.width / 2
//        controlY = drawRect.bottom
//        //lineTo(x, y)
//        quadraticBezierTo(controlX, controlY, x, y)
//    }
//
//    Text(
//        text,
//        modifier = modifier
//            //.size(200.dp)
//            .background(LightGray, shape = balloonShape)
//            .padding(start = tailWidthDp + 6.dp, end = 6.dp)
//            .padding(vertical = 1.dp)
//    )
//}


//@Composable
//fun bezierTest(modifier: Modifier = Modifier) {
//    Canvas(modifier = Modifier.size(300.dp)) {
//
//        val path = Path().apply {
//            moveTo(0f, 0f)
//            quadraticBezierTo(
//                150.dp.toPx(), 300.dp.toPx(),
//                300.dp.toPx(), 0.dp.toPx()
//            )
////            lineTo(270.dp.toPx(), 100.dp.toPx())
////            quadraticBezierTo(
////                60.dp.toPx(), 80.dp.toPx(),
////                0f, 0f)
//
//            close()
//        }
//        rotate(45f) {
//            drawPath(
//                path = path,
//                Color.Blue,
//            )
//        }
//    }
//}
//
//@Composable
//fun Triangle(modifier: Modifier = Modifier) {
//    val triangleShape = GenericShape { size, _ ->
//        moveTo(size.width / 2f, 0f)
//        lineTo(size.width, size.height)
//        lineTo(0f, size.height)
//    }
//
//    Box(
//        modifier = modifier
//            .size(100.dp)
//            .clip(triangleShape)
//            .background(LightGray)
//    )
//}

//
//@Composable
//fun QuadCurveSample() {
//    Canvas(modifier = Modifier.size(200.dp)) {
//        val path = Path().apply {
//            // しっぽの上部
//            moveTo(x = 0.dp.toPx(), y = 80.dp.toPx())   // start point 1
//            quadraticBezierTo(
//                x1 = 100.dp.toPx(), y1 = 0.dp.toPx(),   // control point
//                x2 = 200.dp.toPx(), y2 = 0.dp.toPx()    // end point 2 (start point 2)
//            )
//
//            // しっぽの下部
//            quadraticBezierTo(
//                x1 = 100.dp.toPx(), y1 = 0.dp.toPx(),   // control point
//                x2 = 0.dp.toPx(), y2 = 200.dp.toPx()    // end point 2
//            )
//        }
//        drawPath(
//            path = path,
//            Color.Green
//        )
//    }
//}
//

data class User(
    var name: String,
    var icon: Int
)

data class Message(
    var user: User,
    var text: String,
    var date: String,
    var readCount: Int // 既読数
)

@Composable
fun MessageList(modifier: Modifier = Modifier) {
    val me = User("自分", R.drawable.usagi)
    val you = User("太郎", R.drawable.usagi)

    val messages = listOf(
        Message(you, "こんにちは！", "13:10", 1),
        Message(me, "調子はどうですか？", "13:11", 2),
        Message(you, "順調です！", "13:12", 1),
        Message(
            you,
            "長い文章のサンプルです。見た目は崩れていないでしょうか？サンプルです。サンプルです。サンプルです。サンプルです。サンプルです。",
            "13:12",
            1
        ),
        Message(me, "いいね！", "13:13", 1),
        Message(
            me,
            "長い文章のサンプルです。見た目は崩れていないでしょうか？サンプルです。サンプルです。サンプルです。サンプルです。サンプルです。",
            "13:13",
            0
        )
    )

    LazyColumn(
        modifier = modifier
            .background(Color(red = 211, green = 216, blue = 231))
            .padding(2.dp)
    ) {
        items(messages) { message ->
            MessageItem(me, message)
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
        }
    }
}

// Jetpack Composeで一番大きいコンポーネントにすべて合わせたい
// https://takusan.negitoro.dev/posts/android_jc_parent_size/
//.height(IntrinsicSize.Max) // 時間表示を吹き出しの下端に揃える
@Composable
fun MessageItem(me: User, message: Message) {
    if (message.user != me) {
        Row {
//            Image(
//                painter = painterResource(id = message.user.icon),
//                contentDescription = "",
//                modifier = Modifier
//                    .height(40.dp)
//                    .padding(start = 2.dp, top = 1.dp)
//                    .clip(CircleShape)
//            )
            Column(
                modifier = Modifier.weight(4f, false)
            ) {
                Text(
                    message.user.name,
                    //style = MaterialTheme.typography.caption,
                    fontSize = 8.sp
                )
                Row {
                    YourBalloonText(message.text)
                    Text(
                        message.date,
                        fontSize = 8.sp,
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(start = 1.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    } else {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(4f)
            ) {
                Text(
                    message.user.name,
                    fontSize = 8.sp,
                    textAlign = TextAlign.End,
                    //modifier = Modifier.fillMaxWidth()
                )
                Row {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(end = 2.dp)
                    ) {
                        if (message.readCount > 0) {
                            Text(
                                " 既読" +
                                        if (message.readCount >= 2) {
                                            " ${message.readCount}"
                                        } else {
                                            ""
                                        },
                                fontSize = 8.sp,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                        Text(
                            message.date,
                            fontSize = 8.sp,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    MyBalloonText(message.text)
                }
            }
//            Image(
//                painter = painterResource(id = message.user.icon),
//                contentDescription = "",
//                modifier = Modifier
//                    .height(40.dp)
//                    .padding(start = 2.dp, top = 1.dp)
//                    .clip(CircleShape)
//            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BalloonTextPreview() {
    ComposePlaygroundTheme {
        MessageList()
    }
}
