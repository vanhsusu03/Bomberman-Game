# ***Bomberman Game - Kì 22-23I, INT2204 20, Nhóm TH 3, nhóm 9***

## **Thông tin thành viên**
+ Dương Nguyễn Việt Anh - K66CD
+ Trương Quang Đạt - K66CD
+ Hoàng Văn Nguyên - K66CD

## **Mô tả dự án**
### ***Giới thiệu dự án***
- Ý tưởng phát triển dự án dựa trên tựa game kinh điển Bomberman Game.
- Tại mỗi màn chơi, bạn có nhiệm vụ điều khiển Bomberman tiêu diệt tất cả **"quái vật"**, tìm ra cánh cổng đến với màn chơi tiếp theo.   

### ***Các đối tượng trong trò chơi***
Nếu bạn đã từng chơi Bomberman, bạn sẽ cảm thấy quen thuộc với những đối tượng này. Chúng được được chia làm hai loại chính là nhóm đối tượng động (*Bomber*, *Enemy*, *Bomb*) và nhóm đối tượng tĩnh (*Grass*, *Wall*, *Brick*, *Door*, *Item*).


- ![](res/sprites/player_down.png) *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi. 
- ![](res/sprites/balloom_left1.png) *Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- ![](res/sprites/bomb.png) *Bomb* là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng *Flame* ![](res/sprites/explosion_horizontal.png) được tạo ra.


- ![](res/sprites/grass.png) *Grass* là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó
- ![](res/sprites/wall.png) *Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này
- ![](res/sprites/brick.png) *Brick* là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.


- ![](res/sprites/portal.png) *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.

Các *Item* cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:
- ![](res/sprites/powerup_speed.png) *SpeedItem* Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp
- ![](res/sprites/powerup_flames.png) *FlameItem* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn)
- ![](res/sprites/powerup_bombs.png) *BombItem* Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.
- ![](res/sprites/powerup_bombpass.png) *BombPass* Iteam này giúp Bomber đi xuyên qua Bomb trong game.
- ![](res/sprites/powerup_detonator.png) *Detonator* Iteam này kích hoạt quả Bomb cũ nhất được đặt.
- ![](res/sprites/powerup_flamepass.png) *FlamePass* Iteam này giúp Bomber không bị ảnh hưởng bởi phạm vi của Bomb nổ.
- ![](res/sprites/powerup_wallpass.png) *BrickPass* Iteam này giúp Bomber đi xuyên qua các Brick (Không bao gồm các Wall).
- ![image](https://user-images.githubusercontent.com/113848415/198220331-921c148a-6a99-43be-8fc0-e7b6330f171a.png) *???* Iteam này sẽ là một vật phẩm ngẫu nhiên trong các vật phẩm nêu trên

***Các loại Enemy bạn có thể gặp trong Bomberman Game:***

- ![](res/sprites/balloon_left1.png) *Balloon* là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định
- ![](res/sprites/doll_left1.png) *Doll* là Enemy di chuyển ngẫu nhiên với vận tốc cố định
- ![](res/sprites/oneal_left1.png) *Oneal* có tốc độ di chuyển cố định, đuổi theo người chơi trong một phạm vi nhất định
- ![](res/sprites/minvo_left1.png) *Minvo* có tốc độ di chuyển cố định, đuổi theo người chơi trong một phạm vi nhất định
- ![image](https://user-images.githubusercontent.com/113848415/198219751-c2a76504-2b1c-4e07-b82c-4e5e54187b91.png) *Ovapi* là Enemy đuổi theo người chơi trong một phạm vi nhất định
- ![](res/sprites/kondoria_left1.png) *Kondoria* là Enemy đuổi theo người chơi toàn map
- ![image](https://user-images.githubusercontent.com/113848415/198218279-ed2b07ac-faa1-4452-827a-1e77f8eaa7dd.png) *Pass* là Enemy đuổi theo người chơi toàn map

- ![image](https://user-images.githubusercontent.com/113848415/198218992-b8b118c6-e2c6-4a9a-b03b-7679d7bf4d8a.png) ![image](https://user-images.githubusercontent.com/113848415/198219230-fd7eb0a6-cc28-46c2-86d1-2041f88ee3cf.png) *Pontan* Là Enemies có độ khó cao nhất, xuất hiện sau khi hết giờ, đuổi theo người chơi toàn map với tốc độ cao

## ***Mô tả game play, xử lý va chạm và xử lý bom nổ***
- Trong một màn chơi, Bomber sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Enemy và tìm ra vị trí Portal để có thể qua màn mới
- Bomber sẽ bị giết khi va chạm với Enemy hoặc thuộc phạm vi Bomb nổ. Lúc đấy trò chơi kết thúc.
- Enemy bị tiêu diệt khi thuộc phạm vi Bomb nổ
- Một đối tượng thuộc phạm vi Bomb nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng Bomb nổ.

- Khi Bomb nổ, một Flame trung tâm![](res/sprites/bomb_exploded.png) tại vị trí Bomb nổ và bốn Flame tại bốn vị trí ô đơn vị xung quanh vị trí của Bomb xuất hiện theo bốn hướng trên![](res/sprites/explosion_vertical.png)/dưới![](res/sprites/explosion_vertical.png)/trái![](res/sprites/explosion_horizontal.png)/phải![](res/sprites/explosion_horizontal.png). Độ dài bốn Flame xung quanh mặc định là 1 đơn vị, được tăng lên khi Bomber sử dụng các FlameItem.
- Khi các Flame xuất hiện, nếu có một đối tượng thuộc loại Brick/Wall nằm trên vị trí một trong các Flame thì độ dài Flame đó sẽ được giảm đi để sao cho Flame chỉ xuất hiện đến vị trí đối tượng Brick/Wall theo hướng xuất hiện. Lúc đó chỉ có đối tượng Brick/Wall bị ảnh hưởng bởi Flame, các đối tượng tiếp theo không bị ảnh hưởng. Còn nếu vật cản Flame là một đối tượng Bomb khác thì đối tượng Bomb đó cũng sẽ nổ ngay lập tức.

## ***Preview***
![image](https://user-images.githubusercontent.com/113848415/198330196-6d8ba847-fcae-4717-8940-9b2d9b2f2c97.png)
![image](https://user-images.githubusercontent.com/113848415/198330254-18e54da6-cffd-422d-a9fe-a6e374349b48.png)
![image](https://user-images.githubusercontent.com/113848415/198330459-5a47bda9-9f0f-4ce9-8369-c76bbbbcc152.png)

## **Cây thừa kế cho các đối tượng trong game**

![DiagramBomberman](https://user-images.githubusercontent.com/113848415/198342137-3130ec9c-5179-4cab-bf39-198689f05b20.png)
### ***Link gg drive cây thừa kế: https://drive.google.com/drive/folders/1JoruGmgpcM4d-ICrN5VWyDk0Oz6MFntq?usp=sharing***
## **Điểm cải tiến của dự án**
- Ngôn ngữ lập trình Java, thư viện đồ họa Javafx
- Kĩ thuật lập trình hướng đối tượng
- Cải tiến đồ họa.
- Phát triển âm thanh.
- Tăng tương tác người dùng
- Update thuật toán logic cho enemies (Thuật toán A*)
- Code convention
## **Tổng kết**

### *Điều tâm đắc*

- Project được nghiên cứu và phát triển từ ý tưởng chung của nhóm, không sao chép từ bất kì nguồn nào.

- Cải thiện nhiều kĩ năng lập trình, sử dụng và quản lí file, chương trình.

- Học được cách design hình ảnh, sử dụng photoshop, edit âm thanh,..

### *Điểm hạn chế*

- Quản lí đối tượng chưa thật sự tối ưu.
- Code convention chưa thực sự tốt.
- Cần update đa dạng tính năng và tăng tương tác người dùng.

### *Hướng phát triển tiếp theo*

- Phát triển mô hình client-server.

- Phát triển nhiều chế độ chơi từ dễ đến khó, thêm tính năng cho game.

- Cải thiện chất lượng đồ họa trong game.
- Cải thiện code convention.

