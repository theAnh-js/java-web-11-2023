import cv2
from cvzone.HandTrackingModule import HandDetector
import pyautogui
import pyperclip
import numpy as np
from PIL import ImageFont, ImageDraw, Image
import tkinter as tk
import winsound

# my NEWEST version 2 demo program

def play_beep():
    frequency = 1000  # Tần số của âm thanh (ở đây là 1000 Hz)
    duration = 100  # Thời lượng của âm thanh (ở đây là 100 ms)
    winsound.Beep(frequency, duration)


# Create a tkinter window
root = tk.Tk()
root.title("認識した文字")

root.geometry("600x700")  # Đã thay đổi kích thước cửa sổ để hiển thị textbox lớn hơn

textbox = tk.Text(root, font=("Arial", 30), width=30, height=10)  # Thay đổi width và height ở đây
textbox.pack(pady=20)
# # Tự động focus vào textbox
# textbox.focus_set()

cap = cv2.VideoCapture(0)
cap.set(cv2.CAP_PROP_FRAME_WIDTH, 1920)
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 1080)
cap.set(cv2.CAP_PROP_FPS, 25)

# mojiList = ["a", "i", "u", "e", "o"]

detector = HandDetector(detectionCon=0.9, maxHands=2)
isContinue = True

def putText_japanese(img, text, point, size, color):

    font = ImageFont.truetype('C:\Windows\Fonts\BIZ-UDMinchoM.ttc', size)

    img_pil = Image.fromarray(img)

    draw = ImageDraw.Draw(img_pil)

    draw.text(point, text, fill=color, font=font)

    return np.array(img_pil)

# listAnimal =["いぬ", "ねこ", "きつね"]
# def createText(moji):
#     myText = ""
#     if(moji not in myText):
#         myText += moji
#     if myText in listAnimal:
#         print('犬が見つかっぞ！')

while True:
    success, img = cap.read()
    hands, img = detector.findHands(img)
    # print(len(hands))

    charList = []
    # charList.append("")
    def createmoji(moji):
        print(moji)
        # createText(moji)
        pyperclip.copy(moji)
        pyautogui.hotkey('ctrl', 'v')

    # tay được nhận diện trước sẽ trở thành tay PHẢI.
    if hands:

        # left
        hand1 = hands[0]
        lmList1 = hand1["lmList"]  # 21
        bbox1 = hand1["bbox"]  # x y w h
        centerPoint1 = hand1["center"]  # center of hand
        handType1 = hand1["type"]  # right or left
        fingers1 = detector.fingersUp(hand1)

        # print(len(lmList1), lmList1)
        # print(handType1)
        if len(hands) == 2:
            # right
            hand2 = hands[1]
            lmList2 = hand2["lmList"]
            bbox2 = hand2["bbox"]
            centerPoint2 = hand2["center"]
            handType2 = hand2["type"]
            fingers2 = detector.fingersUp(hand2)

            # right
            isA = lmList2[4][0] > lmList2[3][0] \
                and lmList2[8][1] > lmList2[6][1] \
                and lmList2[12][1] > lmList2[10][1] \
                and lmList2[16][1] > lmList2[14][1] \
                and lmList2[20][1] > lmList2[18][1] \
                and lmList2[20][0] < lmList2[5][0]
            isI = lmList2[4][0] < lmList2[3][0] \
                and lmList2[4][0] < lmList2[11][0]\
                and lmList2[8][1] > lmList2[6][1] \
                and lmList2[12][1] > lmList2[10][1] \
                and lmList2[16][1] > lmList2[14][1] \
                and lmList2[20][1] < lmList2[18][1]
            # and lmList2[4][0] < lmList2[11][0]: phan biet voi E
            isU = lmList2[4][0] < lmList2[3][0] \
                and lmList2[8][1] < lmList2[6][1] \
                and lmList2[12][1] < lmList2[10][1] \
                and lmList2[16][1] > lmList2[14][1] \
                and lmList2[20][1] > lmList2[18][1]
            isE = lmList2[4][0] < lmList2[3][0] \
                and lmList2[4][0] > lmList2[11][0]\
                and lmList2[8][1] > lmList2[6][1]\
                and lmList2[12][1] > lmList2[10][1] \
                and lmList2[16][1] > lmList2[14][1] \
                and lmList2[20][1] > lmList2[18][1] \
                and lmList2[20][0] < lmList2[5][0]
            # and lmList2[4][0] > lmList2[11][0]: phan biet voi I
            isO = lmList2[8][0] > lmList2[6][0] \
                and lmList2[4][0] < lmList2[3][0]\
                and lmList2[12][0] > lmList2[10][0] \
                and lmList2[16][0] > lmList2[14][0] \
                and lmList2[20][0] > lmList2[18][0] \
                and lmList2[8][1] > lmList2[7][1] \
                and lmList2[12][1] > lmList2[11][1] \
                and lmList2[16][1] > lmList2[15][1] \
                and lmList2[20][1] > lmList2[19][1] \
                and lmList2[20][0] > lmList2[5][0]
            isNext = lmList2[4][0] > lmList2[3][0] \
                and lmList2[8][1] < lmList2[6][1] \
                and lmList2[12][1] < lmList2[10][1] \
                and lmList2[16][1] < lmList2[14][1] \
                and lmList2[20][1] < lmList2[18][1] \
                and lmList2[20][0] < lmList2[5][0]
            # ya yu yo for kaiyouon
            # print(fingers2[0])
            # print(fingers2[1])
            isYa = fingers2[0] == 0 and fingers2[1] == 0 and fingers2[2] == 1 and fingers2[3] == 1 and fingers2[4] == 1
            isYu = fingers2[0] == 1 and fingers2[1] == 1 and fingers2[2] == 0 and fingers2[3] == 0 and fingers2[4] == 0
            isYo = fingers2[0] == 0 and fingers2[1] == 1 and fingers2[2] == 0 and fingers2[3] == 0 and fingers2[4] == 0

            # delete
            isDelete = fingers2[0] == 0 and fingers2[1] == 0 and fingers2[2] == 0 \
                                        and fingers2[3] == 0 and fingers2[4] == 0
            # あ　い　う　え　お
            # left: a
            # right: a i u e o
            if lmList1[4][0] < lmList1[3][0] \
                    and lmList1[4][1] < lmList1[3][1] \
                    and lmList1[8][1] > lmList1[6][1] \
                    and lmList1[12][1] > lmList1[10][1] \
                    and lmList1[16][1] > lmList1[14][1] \
                    and lmList1[20][1] > lmList1[18][1]:
                if isA:
                    charList.append("あ")
                    if isContinue:
                        # charList.append("あ")
                        createmoji("あ")
                        isContinue = False
                if isI:
                    charList.append("い")
                    if isContinue:
                        createmoji("い")
                        isContinue = False
                if isU:
                    charList.append("う")
                    if isContinue:
                        createmoji("う")
                        isContinue = False
                if isE:
                    charList.append("え")
                    if isContinue:
                        createmoji("え")
                        isContinue = False
                if isO:
                    charList.append("お")
                    if isContinue:
                        createmoji("お")
                        isContinue = False
                if isNext:
                    isShow = False
                    isContinue = True

            # Left
            # ka sa ta na ha(hi) ma(me) ya ra(re) wa
            # w + o --> を
            # w + e --> ん
            # K
            if lmList1[4][0] > lmList1[3][0] \
                and lmList1[8][1] < lmList1[6][1] \
                and lmList1[12][1] < lmList1[10][1] \
                and lmList1[12][0] > lmList1[8][0] \
                and lmList1[16][1] > lmList1[14][1] \
                and lmList1[20][1] > lmList1[18][1]:
                # print("ka")
                if isA:
                    charList.append("か")

                    if isContinue:
                        createmoji("か")
                        isContinue = False
                if isI:
                    charList.append("き")

                    if isContinue:
                        createmoji("き")
                        isContinue = False
                if isU:
                    charList.append("く")

                    if isContinue:
                        createmoji("く")
                        isContinue = False
                if isE:
                    charList.append("け")

                    if isContinue:
                        createmoji("け")
                        isContinue = False
                if isO:
                    charList.append("こ")

                    if isContinue:
                        createmoji("こ")
                        isContinue = False
                if isYa:
                    charList.append("きゃ")

                    if isContinue:
                        createmoji("きゃ")
                        isContinue = False
                if isYu:
                    charList.append("きゅ")

                    if isContinue:
                        createmoji("きゅ")
                        isContinue = False
                if isYo:
                    charList.append("きょ")

                    if isContinue:
                        createmoji("きょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # S
            if lmList1[4][0] > lmList1[3][0] \
                and lmList1[9][1] < lmList1[0][1] \
                and lmList1[8][1] > lmList1[6][1] \
                and lmList1[12][1] > lmList1[10][1] \
                and lmList1[16][1] > lmList1[14][1] \
                and lmList1[20][1] > lmList1[18][1]\
                and lmList1[8][1] > lmList1[5][1]\
                and lmList1[5][0] < lmList1[18][0]:
                # print("sa")
                if isA:
                    charList.append("さ")

                    if isContinue:
                        createmoji("さ")
                        isContinue = False
                if isI:
                    charList.append("し")

                    if isContinue:
                        createmoji("し")
                        isContinue = False
                if isU:
                    charList.append("す")

                    if isContinue:
                        createmoji("す")
                        isContinue = False
                if isE:
                    charList.append("せ")

                    if isContinue:
                        createmoji("せ")
                        isContinue = False
                if isO:
                    charList.append("そ")

                    if isContinue:
                        createmoji("そ")
                        isContinue = False
                if isYa:
                    charList.append("しゃ")

                    if isContinue:
                        createmoji("しゃ")
                        isContinue = False
                if isYu:
                    charList.append("しゅ")

                    if isContinue:
                        createmoji("しゅ")
                        isContinue = False
                if isYo:
                    charList.append("しょ")

                    if isContinue:
                        createmoji("しょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # T
            if lmList1[5][0] > lmList1[18][0] \
               and lmList1[4][1] < lmList1[3][1] \
                and lmList1[4][1] < lmList1[8][1] \
                and lmList1[4][1] < lmList1[12][1] \
                and lmList1[4][1] < lmList1[16][1] \
                and lmList1[4][1] < lmList1[20][1]:
                # print("ta")
                if isA:
                    charList.append("た")

                    if isContinue:
                        createmoji("た")
                        isContinue = False
                if isI:
                    charList.append("ち")

                    if isContinue:
                        createmoji("ち")
                        isContinue = False
                if isU:
                    charList.append("つ")

                    if isContinue:
                        createmoji("つ")
                        isContinue = False
                if isE:
                    charList.append("て")

                    if isContinue:
                        createmoji("て")
                        isContinue = False
                if isO:
                    charList.append("と")

                    if isContinue:
                        createmoji("と")
                        isContinue = False
                if isYa:
                    charList.append("ちゃ")

                    if isContinue:
                        createmoji("ちゃ")
                        isContinue = False
                if isYu:
                    charList.append("ちゅ")

                    if isContinue:
                        createmoji("ちゅ")
                        isContinue = False
                if isYo:
                    charList.append("ちょ")

                    if isContinue:
                        createmoji("ちょ")
                        isContinue = False
                if isNext:
                    isContinue = True

            # cần quay phía mu của tay phải ra trước để nhận diện qua mu của tay trái được
            # N
            if lmList1[9][1] > lmList1[0][1] \
                and lmList1[8][1] > lmList1[6][1] \
                and lmList1[12][1] > lmList1[10][1] \
                and lmList1[16][1] < lmList1[14][1] \
                and lmList1[20][1] < lmList1[18][1]:
                # print("na")
                if isA:
                    charList.append("な")

                    if isContinue:
                        createmoji("な")
                        isContinue = False
                if isI:
                    charList.append("に")

                    if isContinue:
                        createmoji("に")
                        isContinue = False
                if isU:
                    charList.append("ぬ")

                    if isContinue:
                        createmoji("ぬ")
                        isContinue = False
                if isE:
                    charList.append("ね")

                    if isContinue:
                        createmoji("ね")
                        isContinue = False
                if isO:
                    charList.append("の")

                    if isContinue:
                        createmoji("の")
                        isContinue = False
                if isYa:
                    charList.append("にゃ")

                    if isContinue:
                        createmoji("にゃ")
                        isContinue = False
                if isYu:
                    charList.append("にゅ")

                    if isContinue:
                        createmoji("にゅ")
                        isContinue = False
                if isYo:
                    charList.append("にょ")

                    if isContinue:
                        createmoji("にょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # H
            # cột H thay vì lấy ha thì lấy hi để dễ cho việc tạo hình ngón tay và nhận diện
            if lmList1[4][0] > lmList1[3][0] \
                and lmList1[8][1] < lmList1[6][1] \
                and lmList1[12][1] > lmList1[10][1] \
                and lmList1[16][1] > lmList1[14][1] \
                and lmList1[20][1] > lmList1[18][1]:
                # print("hi")
                if isA:
                    charList.append("は")

                    if isContinue:
                        createmoji("は")
                        isContinue = False
                if isI:
                    charList.append("ひ")

                    if isContinue:
                        createmoji("ひ")
                        isContinue = False
                if isU:
                    charList.append("ふ")

                    if isContinue:
                        createmoji("ふ")
                        isContinue = False
                if isE:
                    charList.append("へ")

                    if isContinue:
                        createmoji("へ")
                        isContinue = False
                if isO:
                    charList.append("ほ")

                    if isContinue:
                        createmoji("ほ")
                        isContinue = False
                if isYa:
                    charList.append("ひゃ")

                    if isContinue:
                        createmoji("ひゃ")
                        isContinue = False
                if isYu:
                    charList.append("ひゅ")

                    if isContinue:
                        createmoji("ひゅ")
                        isContinue = False
                if isYo:
                    charList.append("ひょ")

                    if isContinue:
                        createmoji("ひょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # M
            # cột M thay vì lấy MA thì ta lấy ME để dễ dàng nhận diện vào tay cũng thoải mái hơn.
            if lmList1[4][0] > lmList1[3][0] \
                and lmList1[8][1] > lmList1[6][1] \
                and lmList1[12][1] < lmList1[10][1] \
                and lmList1[16][1] < lmList1[14][1] \
                and lmList1[20][1] < lmList1[18][1]:
                # print("ma")
                if isA:
                    charList.append("ま")

                    if isContinue:
                        createmoji("ま")
                        isContinue = False
                if isI:
                    charList.append("み")

                    if isContinue:
                        createmoji("み")
                        isContinue = False
                if isU:
                    charList.append("む")

                    if isContinue:
                        createmoji("む")
                        isContinue = False
                if isE:
                    charList.append("め")

                    if isContinue:
                        createmoji("め")
                        isContinue = False
                if isO:
                    charList.append("も")

                    if isContinue:
                        createmoji("も")
                        isContinue = False
                if isYa:
                    charList.append("みゃ")

                    if isContinue:
                        createmoji("みゃ")
                        isContinue = False
                if isYu:
                    charList.append("みゅ")

                    if isContinue:
                        createmoji("みゅ")
                        isContinue = False
                if isYo:
                    charList.append("みょ")

                    if isContinue:
                        createmoji("みょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # Y
            if lmList1[4][0] < lmList1[3][0] \
                and lmList1[4][1] < lmList1[3][1] \
                and lmList1[8][1] > lmList1[6][1] \
                and lmList1[12][1] > lmList1[10][1] \
                and lmList1[16][1] > lmList1[14][1] \
                and lmList1[20][1] < lmList1[18][1]:
                # print("ya")
                if isA:
                    charList.append("や")

                    if isContinue:
                        createmoji("や")
                        isContinue = False
                if isU:
                    charList.append("ゆ")

                    if isContinue:
                        createmoji("ゆ")
                        isContinue = False
                if isO:
                    charList.append("よ")

                    if isContinue:
                        createmoji("よ")
                        isContinue = False
                if isI:
                    charList.append("。")

                    if isContinue:
                        createmoji("。")
                        isContinue = False
                if isE:
                    if isContinue:
                        pyautogui.press('enter')
                        isContinue = False
                if isNext:
                    isContinue = True
            # R
            # ở cột R, nếu lấy RA thì khi tạo ra, KA sẽ bị dính vào,
            # nên lấy RE thay vào.
            if lmList1[4][0] < lmList1[3][0] \
                and lmList1[8][1] < lmList1[6][1] \
                and lmList1[12][1] > lmList1[10][1] \
                and lmList1[16][1] > lmList1[14][1] \
                and lmList1[20][1] > lmList1[18][1]:
                # print("ra")
                if isA:
                    charList.append("ら")

                    if isContinue:
                        createmoji("ら")
                        isContinue = False
                if isI:
                    charList.append("り")

                    if isContinue:
                        createmoji("り")
                        isContinue = False
                if isU:
                    charList.append("る")

                    if isContinue:
                        createmoji("る")
                        isContinue = False
                if isE:
                    charList.append("れ")

                    if isContinue:
                        createmoji("れ")
                        isContinue = False
                if isO:
                    charList.append("ろ")

                    if isContinue:
                        createmoji("ろ")
                        isContinue = False
                if isYa:
                    charList.append("りゃ")

                    if isContinue:
                        createmoji("りゃ")
                        isContinue = False
                if isYu:
                    charList.append("りゅ")

                    if isContinue:
                        createmoji("りゅ")
                        isContinue = False
                if isYo:
                    charList.append("りょ")

                    if isContinue:
                        createmoji("りょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # W
            if lmList1[4][0] > lmList1[3][0] \
                and lmList1[8][1] < lmList1[6][0] \
                and lmList1[12][1] < lmList1[10][1] \
                and lmList1[16][1] < lmList1[14][1] \
                and lmList1[20][1] > lmList1[18][1]:
                # print("wa")
                if isA:
                    charList.append("わ")

                    if isContinue:
                        createmoji("わ")
                        isContinue = False
                if isU:
                    charList.append("を")

                    if isContinue:
                        createmoji("を")
                        isContinue = False
                if isE:
                    charList.append("ん")

                    if isContinue:
                        createmoji("ん")
                        isContinue = False
                if isNext:
                    isContinue = True
            #B
            if lmList1[4][0] > lmList1[3][0] \
                and lmList1[8][1] > lmList1[6][1] \
                and lmList1[12][1] > lmList1[10][1] \
                and lmList1[16][1] > lmList1[14][1] \
                and lmList1[20][1] < lmList1[18][1]:
                # print("ba")
                if isA:
                    charList.append("ば")

                    if isContinue:
                        createmoji("ば")
                        isContinue = False
                if isI:
                    charList.append("び")

                    if isContinue:
                        createmoji("び")
                        isContinue = False
                if isU:
                    charList.append("ぶ")

                    if isContinue:
                        createmoji("ぶ")
                        isContinue = False
                if isE:
                    charList.append("べ")

                    if isContinue:
                        createmoji("べ")
                        isContinue = False
                if isO:
                    charList.append("ぼ")

                    if isContinue:
                        createmoji("ぼ")
                        isContinue = False
                if isYa:
                    charList.append("びゃ")

                    if isContinue:
                        createmoji("びゃ")
                        isContinue = False
                if isYu:
                    charList.append("びゅ")

                    if isContinue:
                        createmoji("びゅ")
                        isContinue = False
                if isYo:
                    charList.append("びょ")

                    if isContinue:
                        createmoji("びょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # P
            if lmList1[4][0] > lmList1[3][0] \
                and lmList1[8][1] > lmList1[6][1] \
                and lmList1[12][1] > lmList1[10][1] \
                and lmList1[16][1] < lmList1[14][1] \
                and lmList1[20][1] < lmList1[18][1]:
                # print("pa")
                if isA:
                    charList.append("ぱ")

                    if isContinue:
                        createmoji("ぱ")
                        isContinue = False
                if isI:
                    charList.append("ぴ")

                    if isContinue:
                        createmoji("ぴ")
                        isContinue = False
                if isU:
                    charList.append("ぷ")

                    if isContinue:
                        createmoji("ぷ")
                        isContinue = False
                if isE:
                    charList.append("ぺ")

                    if isContinue:
                        createmoji("ぺ")
                        isContinue = False
                if isO:
                    charList.append("ぽ")

                    if isContinue:
                        createmoji("ぽ")
                        isContinue = False
                if isYa:
                    charList.append("ぴゃ")

                    if isContinue:
                        createmoji("ぴゃ")
                        isContinue = False
                if isYu:
                    charList.append("ぴゅ")

                    if isContinue:
                        createmoji("ぴゅ")
                        isContinue = False
                if isYo:
                    charList.append("ぴょ")

                    if isContinue:
                        createmoji("ぴょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # D
            if lmList1[4][0] > lmList1[3][0] \
                    and lmList1[8][1] < lmList1[6][1] \
                    and lmList1[12][1] < lmList1[10][1] \
                    and lmList1[16][1] < lmList1[14][1] \
                    and lmList1[20][1] < lmList1[18][1]:
                # print("DA")
                if isA:
                    charList.append("だ")

                    if isContinue:
                        createmoji("だ")
                        isContinue = False
                if isI:
                    charList.append("ぢ")

                    if isContinue:
                        createmoji("ぢ")
                        isContinue = False
                if isU:
                    charList.append("づ")

                    if isContinue:
                        createmoji("づ")
                        isContinue = False
                if isE:
                    charList.append("で")

                    if isContinue:
                        createmoji("で")
                        isContinue = False
                if isO:
                    charList.append("ど")

                    if isContinue:
                        createmoji("ど")
                        isContinue = False
                if isYa:
                    charList.append("ぢゃ")

                    if isContinue:
                        createmoji("ぢゃ")
                        isContinue = False
                if isYu:
                    charList.append("ぢゅ")

                    if isContinue:
                        createmoji("ぢゅ")
                        isContinue = False
                if isYo:
                    charList.append("ぢょ")

                    if isContinue:
                        createmoji("ぢょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # Z
            if lmList1[4][0] > lmList1[3][0] \
                    and lmList1[8][1] < lmList1[6][1] \
                    and lmList1[12][1] > lmList1[10][1] \
                    and lmList1[16][1] < lmList1[14][1] \
                    and lmList1[20][1] < lmList1[18][1]:
                # print("ZA")
                if isA:
                    charList.append("ざ")

                    if isContinue:
                        createmoji("ざ")
                        isContinue = False
                if isI:
                    charList.append("じ")

                    if isContinue:
                        createmoji("じ")
                        isContinue = False
                if isU:
                    charList.append("ず")

                    if isContinue:
                        createmoji("ず")
                        isContinue = False
                if isE:
                    charList.append("ぜ")

                    if isContinue:
                        createmoji("ぜ")
                        isContinue = False
                if isO:
                    charList.append("ぞ")

                    if isContinue:
                        createmoji("ぞ")
                        isContinue = False
                if isYa:
                    charList.append("じゃ")

                    if isContinue:
                        createmoji("じゃ")
                        isContinue = False
                if isYu:
                    charList.append("じゅ")

                    if isContinue:
                        createmoji("じゅ")
                        isContinue = False
                if isYo:
                    charList.append("じょ")

                    if isContinue:
                        createmoji("じょ")
                        isContinue = False
                if isNext:
                    isContinue = True
            # G
            if lmList1[4][0] > lmList1[3][0] \
                    and lmList1[8][1] < lmList1[6][1] \
                    and lmList1[12][1] > lmList1[10][1] \
                    and lmList1[16][1] > lmList1[14][1] \
                    and lmList1[20][1] < lmList1[18][1]:
                # print("が")
                if isA:
                    charList.append("が")

                    if isContinue:
                        createmoji("が")
                        isContinue = False
                if isI:
                    charList.append("ぎ")

                    if isContinue:
                        createmoji("ぎ")
                        isContinue = False
                if isU:
                    charList.append("ぐ")

                    if isContinue:
                        createmoji("ぐ")
                        isContinue = False
                if isE:
                    charList.append("げ")

                    if isContinue:
                        createmoji("げ")
                        isContinue = False
                if isO:
                    charList.append("ご")

                    if isContinue:
                        createmoji("ご")
                        isContinue = False
                if isYa:
                    charList.append("ぎゃ")

                    if isContinue:
                        createmoji("ぎゃ")
                        isContinue = False
                if isYu:
                    charList.append("ぎゅ")

                    if isContinue:
                        createmoji("ぎゅ")
                        isContinue = False
                if isYo:
                    charList.append("ぎょ")

                    if isContinue:
                        createmoji("ぎょ")
                        isContinue = False
                if isNext:
                    isContinue = True

            # っ　(つ　nho)
            # với tsu nhỏ cần quay mu tay về phía cam để có thể nhận diện được lmList1[4][1] > lmList1[3][1]
            # if lmList1[5][0] > lmList1[18][0] \
            #     and lmList1[3][1] < lmList1[4][1] < lmList1[8][1] \
            #     and lmList1[4][1] < lmList1[12][1] \
            #     and lmList1[4][1] < lmList1[16][1] \
            #     and lmList1[4][1] < lmList1[20][1]:
            if lmList1[4][0] < lmList1[3][0] \
                    and lmList1[8][1] < lmList1[6][1] \
                    and lmList1[12][1] < lmList1[10][1] \
                    and lmList1[16][1] < lmList1[14][1] \
                    and lmList1[20][1] < lmList1[18][1]:
                # print("small tsu")
                if isU:
                    charList.append("っ")

                    if isContinue:
                        createmoji("っ")
                        isContinue = False
                if isDelete:
                    if isContinue:
                        pyautogui.press("backspace")
                        isContinue = False
                if isA:
                    if isContinue:
                        pyautogui.press('left')
                        isContinue = False
                if isI:
                    if isContinue:
                        pyautogui.press('right')
                        isContinue = False
                if isNext:
                    isContinue = True
            else:
                charList.append("")
        else:
            charList.append("")
        # mytext = charList[0]

        # img = putText_japanese(img, mytext, (500, 60), 150, (255, 140, 0))

    def on_key_press(event):
        play_beep()  # Gọi hàm play_beep() để phát ra âm thanh

    # Gắn sự kiện on_key_press vào textbox
    textbox.bind("<Key>", on_key_press)

    # Update the tkinter window to handle textbox input
    root.update()

    cv2.imshow("yu-bi-mo-ji", img)
    if cv2.waitKey(1) == ord("q"):  # độ trễ 1/1000s , nếu bấm q sẽ thoát
        print("the end of program")
        break

cap.release()  # giải phóng camera
cv2.destroyAllWindows()
