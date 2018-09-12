import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.security.Principal;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import static java.lang.Math.toIntExact;

public class LandmarkBot extends TelegramLongPollingBot {

    //public final String chat_id = "-275632683";
    public final String chat_id = "@landmarkvietnam";

    String stringYoutubeLink = "Kênh FBNC – kênh truyền hình tài chính kinh tế:\n" +
            "https://youtu.be/eUhXV4BPT-8\n" +
            "Event ngày 25/8:\n" +
            "https://youtu.be/2uBH1giI7i8\n" +
            "Mr Sam nói về Token LAC Việt sub:\n" +
            "https://youtu.be/hMxG_sw49KA\n" +
            "Mr Sam nói về lịch sử Landmark Asia Việt sub:\n" +
            "https://youtu.be/B37KVcnsy6s\n" +
            "\n";

    String stringBaoChi = "•\tCác tài liệu liên quan :\n" +
            "Website thông tin chính thức :  http://landmarkasia.net\n" +
            "Website ICO : http://landmarkasia.io\n" +
            "Các kênh mạng xã hội chính thức sẽ có sau khi đội marketing quốc tế bắt đầu sau giai đoạn Private.\n" +
            "•\tGroup thảo luận của Landmark Việt Nam: https://t.me/landmarkvietnam\n" +
            "Group FB thảo luận:  https://www.facebook.com/groups/landmarkvietnam\n" +
            "•\tTelegram thảo luận: https://t.me/landmarkvietnam\n" +
            "Hướng dẫn mua Private https://youtu.be/deubPhxWfjg hoặc file PDF http://bit.ly/huongdandangkylandmark\n" +
            "Sự thay đổi mức Min ETH trong Private: https://www.facebook.com/groups/landmarkvietnam/permalink/1879227398827163/\n" +
            "https://blogtienao.com/landmark-token-la-gi-review-ve-du-an-landmark-coin/\n" +
            "•\thttp://cafef.vn/thuong-hieu-landmark-entertainment-asia-ra-mat-tai-viet-nam-2018083016383368.chn\n" +
            "https://fingazeta.ru/finance/industry/449639/\n" +
            "•\thttp://bigrussia.org/post/read/2396\n" +
            "•\thttps://forklog.com/glava-kompanii-landmark-sem-chi-blokchejn-poshatnet-gegemoniyu-gollivuda-v-kinoindustrii/\n" +
            "•\thttp://www.vsp.ru/2018/08/07/sem-chi-hotel-by-uvidet-filmy-gde-russkie-ne-podletsy-a-geroi/\n" +
            "•\thttp://vietnamnet.vn/vn/giai-tri/the-gioi-sao/quynh-chi-xuat-hien-rang-ro-trong-vai-tro-nha-san-xuat-phim-473210.html\n" +
            "•\thttp://www.yan.vn/mc-quynh-chi-dien-vay-xuyen-thau-sanh-doi-tinh-cam-ben-hua-vi-van-177599.html\n" +
            "•\thttps://vtc.vn/mc-quynh-chi-dien-vay-xuyen-thau-tinh-tu-sanh-doi-voi-hua-vi-van-d422669.html\n" +
            "•\thttps://giaitri.vnexpress.net/photo/sau-man-anh/quynh-chi-mac-xuyen-thau-du-su-kien-3798363.html\n" +
            "•\thttp://tiin.vn/chuyen-muc/sao/quynh-chi-hua-vi-van-sanh%C2%A0doi-tai-le-ky-ket-hop-tac-voi-nha-dau-tu-phim-transformer.html\n" +
            "•\thttps://cryptolightnews.com/le-ra-mat-landmark-asia-holdings-tai-viet-nam/\n" +
            "•\thttps://dantri.com.vn/sao-viet/ba-me-mot-con-quynh-chi-rang-ro-sanh-doi-cung-hua-vi-van-20180827120657197.htm\n" +
            "•\thttps://ione.vnexpress.net/tin-tuc/sao/viet-nam/quynh-chi-hop-tac-voi-nha-dau-tu-phim-transformers-3798332.html\n" +
            "•\thttp://vi.blogtamsu.com/quynh-chi-xuat-hien-rang-ro-trong-vai-tro-nha-san-xuat-phim.html\n" +
            "•\thttp://afamily.vn/nhan-phuc-vinh-banh-bao-xuat-hien-khi-nha-phuong-lam-dam-hoi-voi-truong-giang-2018082613460811.chn\n" +
            "\n";

    String stringLandmarkCoin = "Hiện tại để việc một người Việt Nam hay nhà đầu tư đến từ các quốc gia khác đầu tư vào một bộ phim Hollywood hay một sản phẩm của K-pop phải có rất nhiều thủ tục giấy tờ, mất nhiều chi phí và thời gian và tất nhiên phải có cả mối quan hệ mới làm được điều này. Để giải quyết bài toán này Landmark Asia Holdings đã áp dụng công nghệ blockchain vào việc này không những thế họ còn tạo ra một hệ sinh thái. Trong hệ sinh thái mới này, một chương mới mở ra cho bất kỳ ai bắt đầu khởi nghiệp, đầu tư và tham gia vào ý tưởng của họ. Là cơ hội không thể tuyệt vời hơn của các Startup trẻ khi các bạn có nhiều ý tưởng nhưng gặp khó khăn trong việc kêu gọi vốn đầu tư, với nền tảng công nghệ của Landmark Asia các bạn có thể cho mọi người trên thế giới biết về project của mình và tham gia vào dự án của các bạn thông qua việc đầu tư hay tham vào với bất kỳ vai trò nào nếu thực sự có đủ năng lực. Ngược lại các nhà đầu tư được quyền chọn lựa dự án mà mình sẽ đầu tư, từ các bộ phim Hollywood, phim Hàn Quốc, K-pop, phim điện ảnh Việt Nam, các dự án bất động sản, công nghệ thế giới ảo một cách rất dễ dàng thông qua Landmark Token. Landmark Token hiểu đơn giản là một cổ phần khi có được cổ phần này bạn hiển nhiên trở thành một cổ đông của Landmark Asia và có thể cùng họ đầu tư vào bất kỳ dự án nào bạn muốn.\n" +
            "Với dự án mang tên Landmark Coin mọi người ở bất kỳ đâu trên thế giới đều có thể trở thành một phần của Landmark Asia. Vào tháng 9 này Landmark Coin mở bán Token giai đoạn Private cho 9 quốc gia được chọn làm Master Nodes cụ thể các thông tin như sau :\n" +
            "\n" +
            "Tên dự án: Landmark Coin\n" +
            "•\tKý hiệu Token: LAC\n" +
            "•\tNền tảng: Blockchain Ethereum tiêu chuẩn ERC-20\n" +
            "•\tTổng lượng coin phát hành: 3.000.000.000LAC\n" +
            "•\tToken bán trong đợt sale: 43,30% tức 1.299.000.000LAC\n" +
            "•\tGiá \n" +
            "Private: 0.5$/1 LAC – 70 triệu LAC\n" +
            "Presale: 0.95-1.3$ -230 triệu LAC\n" +
            "ICO :LAC= 2$++ - 999 triệu LAC  \n" +
            "•\tChấp nhận thanh toán: Ethereum(ETH)\n" +
            "•\tĐăng ký  KYC: Bắt buộc\n" +
            "•\tQuốc gia bị hạn chế: Mỹ,Trung Quốc\n" +
            "•\tThời gian ICO:\n" +
            "o\tPresale: 9/2018\n" +
            "o\tICO: 11/2018\n" +
            "o\tDự kiến sẽ listing vào tháng 12 : Binance,Upbit,Bithumb,Crebitex,Bitonbay……\n" +
            "\n" +
            "Nhìn qua giá bán của 3 giai đoạn ta thấy có sự chênh lệch lớn khi người mua ở Private thì lên giai đoạn ICO đã lời gấp 4 lần nhưng yên tâm đây là lý giải của Landmark Asia Holdings :\n" +
            "_ Private và presale hướng tới nhà đầu tư mà Landmark Asia Holding đặt Master Nodes, giai đoạn này đều có mức mua tối thiểu cao nên không phải nhà đầu tư nào cũng có thể tham gia, hơn nữa token mua trong giai đoạn này bị khóa (1 tháng sau khi lên sàn sẽ trả 30% và lần lượt trả 20%,20%,30% cho 3 tháng tiếp theo) nên đảm bảo sẽ không có xả hàng làm thấp hơn giá ICO lúc listing.\n" +
            "_Đầu tư tài chính luôn tiềm ẩn rủi ro nên chúng tôi dành quyền lợi nhiều hơn cho các nhà đầu tư tin tưởng chúng tôi những ngày đầu tiên và phải bỏ ra số tiền lớn ở giai đoạn này là điều hoàn toàn hợp lý.\n" +
            "_Với hệ sinh thái trong tương lai, với các dự án mà chúng tôi định làm thì sau khi được sự tư vấn từ đội ngũ cố vấn nhất là từ ngài Alexander Shulgin cũng như đội cố vấn chiến lược của các sàn chúng tôi dự kiến sẽ listing thì giá ICO 2$+ là con số mà chúng tôi đã được những đội ngũ kể trên tư vấn.\n" +
            "\n" +
            "Như vậy qua sự giải thích trên nếu các bạn mua được Token trong giai đoạn Private thì sau khi lên sàn 1 tháng cứ cho là giá bán bằng mức ICO thì cũng đã thu hồi vốn và lãi 5% và sẽ lãi gấp 4 lần số vốn ban đầu nếu giá vẫn giữ cho đến 3 tháng tiếp theo vậy đâu là sự đảm bảo cho sự tang trưởng và ổn định giá của LAC :\n" +
            "_Sau khi lên sàn các nhà đầu tư tại Mỹ và Trung Quốc là các quốc gia có nền điện ảnh hàng đầu thế giới không được tham gia ICO lần này họ sẽ là những người cần LAC nhất để đầu tư vào các dự án phim Hollywood, chính họ sẽ là những người làm giá LAC tăng trưởng bên cạnh những nhà đầu tư muốn tham gia các project của chúng tôi hoặc do chính các bạn tạo ra.\n" +
            "_Tính minh bạch mà Blockchain mang lại đảm bảo sự an tâm cho nhà đầu tư.\n" +
            "Sự khác biệt với các đồng coin khác ?\n" +
            "_Đây là dự án đầu tiên trên thế giới mà các bạn có thể dùng token này để đầu tư vào phim Hollywood một điều mà hiện tại nếu muốn các bạn cũng khó có thể làm được.\n" +
            "_Tạo ra một nền tảng mới cho các startup kêu gọi vốn thay vì phải huy động vốn theo hình thức truyền thống.\n" +
            "_Mọi người ở bất kỳ nơi đâu trên thế giới đều có thể cùng nhau tham gia một dự án với các vai trò khác nhau, hoặc cùng nhau thực hiện các dự án từ thiện ở bất kỳ nơi đâu trên thế giới như người một nhà.\n" +
            "_Với dự kiến sẽ listing lên các sàn lớn như Binance,bithumb,upbit và nhiều sàn khác thì việc để một đồng coin xuống thấp hơn ICO sẽ là rất khó để xảy ra vì sẽ ảnh hưởng đến danh tiếng của sàn và đội ngũ chiến lược của LAC sẽ không để điều này xảy ra.\n" +
            "\n" +
            "Tổng quan : Đây là dự án đầu tiên mở ra hướng đi mới cho các starup trẻ cũng cho cả nhà đầu tư, vì như các bạn được biết việc 1 starup công nghệ trong 1, 2 năm có thể trở thành một công ty với vốn hóa 1 tỷ đô la như Uber hay Grab là chuyện hết sức bình thường. Với nền tảng của LAC việc đầu tư vào một bộ phim Hollywood hay một dự án nào đó xuyên quốc gia sẽ rất đơn giản không tốn quá nhiều chi phí giấy tờ hay thủ tục. Ngoài việc sinh lời từ việc trao đổi mua bán token trên các sàn giao dịch thì việc đầu tư vào các dự án mà trong tương lai Landmark Asia sẽ làm cũng là một cách đầu tư khá an toàn trong thời điểm này để đảm bảo lợi nhuận.\n" +
            "\n" +
            "\n";

    String stringLandmarkAsia = "Landmark Entertainment Asia là công ty đầu tư toàn cầu hàng đầu Hàn Quốc được thành lập vào năm 2001 với những lĩnh vực hoạt động chủ yếu là  phim truyện, phim hoạt hình, phát triển dự án , đầu từ và sản xuất với tên gọi đầu tiên là PMG - People Management Group. Sau 17 năm hình thành và phát triển, Landmark  Asia đã có những dựa án đầu tư trực tiếp vào thị trường điện ảnh thế giới, một lượng lớn vào các sản phẩm sở hữu trí tuệ toàn cầu và là đối tác không thể thiếu của Landmark USA.Vậy nên Landmark Entertainment Asia không phải là Landmark của bác Phạm Nhật Vượng cả nhé, mọi người có thể tìm trên google landmarkusa.com thì thấy Landmark là cái tên đã có từ rất lâu, LandmarkUSA hay Landmark Asia đều là một phần của Landmark Entertainment Group .CEO của LEA là ông Sam Chi, thành viên của rất nhiều những công ty là tập đoàn uy tín như Global Technical Finance, Price Waterhouse đặc biệt Sam từng có khoảng thời gian 5 năm làm phó chủ tịch tại JPmorgan Chase - hiện tại là tập đoàn đầu tư tài chính lớn nhất nước mỹ với giá trị tài sản lên tới 2.534 tỷ USD.Một dấu mốc đáng nhớ của Sam vào năm 2003 khi ông đầu tư vào bộ phim “Oldboy”- bộ phim đã đặt giải thưởng danh giá nhất của làng điện ảnh thế giới Cannes năm 2004 – đánh dấu sự tha đổi của nền điện ảnh Hàn trong suốt những năm tiếp theo. Landmark Asia Holdings là tên công ty thuộc LEA sẽ thực hiện quá trình huy động vốn.\n" +
            "\n";

    String stringBountyLeader = "Nếu bạn là một Leader có mong muốn hợp tác với Landmark Việt Nam để giúp chúng tôi phát triển cộng đồng để nhận thêm % bonus thì hãy liên hệ qua mail Nova.mkt@landmarkasia.io";

    String stringSamChi = "Sam Chi là một doanh nhân người Mỹ gốc Hàn đã có 25 năm kinh nghiệm và mạng lưới làm việc trong nhiều lĩnh vực đa dạng của ngành công nghiệp kinh doanh. Sau khi tốt nghiệp Đại học Texas tại Austin (Mỹ) với bằng cử nhân và thác sĩ Quản trị Kinh doanh, ông bắt đầu công việc là đối tác liên kết với Price Waterhouse Coopers, một trong những công ty kế toán có uy tín và lớn nhất trên thế giới trong 2 năm từ 1993 đến 1995. Từ 1998 đến năm 2002, Sam dấn thân vào thế giới ngân hàng đầu tư với tư cách là phó chủ tịch của JP Morgan Chase, một trong những ngân hàng đầu tư hàng đầu trong lịch sử với vốn hóa $ 400 tỷ USD và tổng tài sảnhơn $ 2 tỷ USD. Năm 2001, trong khi vẫn ở JP Morgan, ông thành lập công ty đầu tiên của mình, PMG (Nhóm quản lý nhân sự) Food System, sau đó PMG Entertainment và PMG Bio Pharming (2004), bao gồm các lĩnh vực nhà hàng và hậu cần thực phẩm để xem phim cho nhạc kịch đến âm nhạc cho đến các dự án giải trí khác, cũng như công nghệ sinh học và dược phẩm, v.v.\n" +
            "Sau đó, Sam hợp tác với Landmark Entertainment Group USA, một trong những công ty hàng đầu trong lĩnh thiết kế công viên chủ đề và nhà sản xuất thu hút trên thế giới, ông cũng thành lập công ty mang tên Landmark Entertainment Asia vào năm 2005.\n" +
            "\n" +
            "\n" +
            "Sam trở thành chủ tịch của LEA năm 2006 và vẫn làm việc sát sao với Landmark USA để thiết kế và xây dựng những công viên trò chơi vui nhộn và đặc sắc nhất trên thế giới. Cùng với đó, LEA tiếp tục các hoạt động kinh doanh trong lĩnh vực giải trí và bất động sản cũng như các dự án đầy thành công khác xuất phát từ những ngày ở PMG. Vào năm 2012, Sam thành lập quỹ đầu tư Texas Investment, cánh tay phải của Landmark Asia thành cho tài chính, đầu tư và tư vấn cho nhiều dự án của LEA. \n" +
            "Từ bộ phim Hàn Quốc được cả thế giới ngả mũ “Old Boy” - bộ phim đạt giải Grand Prix trong liên hoan phim Quốc tế Cannes năm 2004 tới hợp tác với những ngôi sao có sức ảnh hưởng toàn cầu như Michael Bay, Simon Fuller...vv Sam và Landmark hướng đến việc mang những dự án tuyệt vời đến cho bạn, dù bạn ở bất cứ đâu hoặc bạn muốn tham gia bất cứ dự án như thế nào.\n";

    String stringBounty = "Chương trình Bounty trị giá 100.000 USD dành riêng cho thành viên Việt Nam,các bạn có thể tham gia cả Bounty do đội marketing quốc tế tổ chức diễn ra sau private. Chương trình Bounty sẽ gồm các nội dung sau đây :\n" +
            "_ Facebook : Tham gia group chia sẽ bài ghim kèm các hangtag sau #landmarkasiaholdings #landmarkcoin #landmarkvietnam \n" +
            "_Telegram : Tham gia group và channel Landmark Việt Nam \n" +
            "https://t.me/landmarkvietnam\n" +
            "https://t.me/landmarkvietnamnews\n" +
            "Làm xong 2 nhiệm vụ trên các bạn sẽ nhận được 5$, token sẽ được trả sau 4 tuần khi ICO kết thúc.\n" +
            "Ngoài ra các nhiệm vụ khác sẽ nhận thêm từ 5-20$ có ở mẫu đăng kí dưới đây: https://docs.google.com/forms/d/e/1FAIpQLSes6_E1d55-13UwvpTY712T5WxDGtHeHJ5qgLFamX3WboEndQ/viewform?usp=sf_link\n" +
            "Đội ngũ Landmark Việt Nam sẽ đánh giá dựa trên số lượt xem, tương tác, chất lượng bài viết, video và sẽ không có con số cụ thể cho các tiêu chí đánh giá này,chúng tôi sẽ dành những phần thưởng riêng cho những thành viên tích cực trong việc quảng bá hình ảnh Landmark Asia tại Việt Nam chương trình Bounty sẽ kết thúc trước ngày ICO bắt đầu hàng tuần chúng tôi sẽ cập nhật số phần thưởng mà mọi người nhận được ở đây:\n" +
            "https://docs.google.com/forms/d/e/1FAIpQLSes6_E1d55-13UwvpTY712T5WxDGtHeHJ5qgLFamX3WboEndQ/viewform?usp=sf_link\n" +
            "Các link báo tham khảo : \n" +
            "https://blogtienao.com/landmark-token-la-gi-review-ve-du-an-landmark-coin/\n" +
            "http://cafef.vn/thuong-hieu-landmark-entertainment-asia-ra-mat-tai-viet-nam-2018083016383368.chn\n" +
            "\n";

    String stringNoiQuy = "Xin chào mọi người đây là Group thông tin và hỗ trợ về dự án Landmark Coin của tập đoàn Landmark Asia Holdings tại Việt Nam, chúng tôi sẽ thông tin tất cả những gì chúng tôi được biết về dự án này trên các group ngoài ra các câu hỏi khác  bạn vui lòng gửi mail theo địa chỉ support@landmarkasia.io hoặc tại website landmarkasia.io và landmarkasia.net.\n" +
            "Để tránh bị ra đảo các bạn vui lòng đọc kỹ thông tin sau:\n" +
            "_ Không đăng các link khác không liên quan đến dự án trong group\n" +
            "_ Không chửi bậy, xúc phạm các thành viên khác\n" +
            "_ Không kêu gọi thành viên group tham gia các group của bạn\n";

    String stringThongTin="Nếu bạn là một Leader có mong muốn hợp tác với Landmark Việt Nam để giúp chúng tôi phát triển cộng đồng để nhận thêm % bonus thì hãy liên hệ qua mail Nova.mkt@landmarkasia.io.\n" +
            "\uD83C\uDF33Link tham gia chương trình Bounty dành riêng cho thành viên Việt Nam : https://www.facebook.com/groups/landmarkvietnam/permalink/1871673479582555/\n" +
            "\uD83C\uDF33Các link báo trong nước và quốc tế đưa tin về dự án cũng như giới thiệu quỷ đầu tư Landmark tại Việt Nam sẽ được liên tục cập nhật ở đây :\n" +
            "https://blogtienao.com/landmark-token-la-gi-review-ve-du-an-landmark-coin/\n" +
            "http://cafef.vn/thuong-hieu-landmark-entertainment-asia-ra-mat-tai-viet-nam-2018083016383368.chn\n" +
            "http://vietnamnet.vn/vn/giai-tri/the-gioi-sao/quynh-chi-xuat-hien-rang-ro-trong-vai-tro-nha-san-xuat-phim-473210.html\n" +
            "http://www.yan.vn/mc-quynh-chi-dien-vay-xuyen-thau-sanh-doi-tinh-cam-ben-hua-vi-van-177599.html\n" +
            "https://vtc.vn/mc-quynh-chi-dien-vay-xuyen-thau-tinh-tu-sanh-doi-voi-hua-vi-van-d422669.html\n" +
            "https://giaitri.vnexpress.net/photo/sau-man-anh/quynh-chi-mac-xuyen-thau-du-su-kien-3798363.html\n" +
            "http://tiin.vn/chuyen-muc/sao/quynh-chi-hua-vi-van-sanh%C2%A0doi-tai-le-ky-ket-hop-tac-voi-nha-dau-tu-phim-transformer.html\n" +
            "https://cryptolightnews.com/le-ra-mat-landmark-asia-holdings-tai-viet-nam/\n" +
            "https://dantri.com.vn/sao-viet/ba-me-mot-con-quynh-chi-rang-ro-sanh-doi-cung-hua-vi-van-20180827120657197.htm\n" +
            "https://ione.vnexpress.net/tin-tuc/sao/viet-nam/quynh-chi-hop-tac-voi-nha-dau-tu-phim-transformers-3798332.html\n" +
            "http://vi.blogtamsu.com/quynh-chi-xuat-hien-rang-ro-trong-vai-tro-nha-san-xuat-phim.html\n" +
            "http://afamily.vn/nhan-phuc-vinh-banh-bao-xuat-hien-khi-nha-phuong-lam-dam-hoi-voi-truong-giang-2018082613460811.chn";

    String stringRoadMap="Đúng như Slogan của Landmark Asia – The voice of everyone – tiếng nói của mọi người, sau khi nhận được khá nhiều inbox cũng như mail phản hồi về mức giá bán LAC Private tại Master Node Việt Nam, đội ngũ hỗ trợ của Master Node Việt Nam đã gửi qua Landmark Asia, sau cuộc họp họ đã đồng ý giảm mức giá tối thiểu cho giai đoạn Private xuống 20 ETH cho 10 ngày đầu tiên mở bán. Đây là cơ hội không thể tuyệt vời hơn để các bạn trở thành một thành viên của Landmark Asia. Thông tin cụ thể về lịch mở bán giai đoạn Private sẽ bắt đầu từ 0h 07/09/2018 giờ UTC tức là 7H ngày 07/09/2018 giờ Việt Nam với mức mua như sau :\n" +
            "07/09/2018 – 17/09/2018 tối thiểu 20 ETH, tối đa 2000 ETH\n" +
            "17/09/2018 – 27/09/2018 tối thiểu 50 ETH, tối đa 2000 ETH\n" +
            "Tổng lượng Token bán ra trong Private là 70 triệu Token cho 9 quốc gia trên tổng số 1 tỷ 299 triệu Token sẽ bán ra.\n" +
            "Lưu ý đây là giai đoạn Private các Master Nodes sẽ có chiến lược marketing riêng, các thông tin khác sẽ được Landmark Asia công khai trong chiến dịch marketing quốc tế của họ sau Private nên các bạn vui lòng không hỏi gì thêm về vấn đề này";
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String command = update.getMessage().getText();

            SendMessage sendMessage = new SendMessage();

            checkMessage(command,update);

            //replaceDirtywords(update,command);//ẩn từ ngữ thô tục


            switch (command) {
                case "/hello":
                    sendHello(update);
                    break;

                case "/hello@LandmarkAsia_bot":
                    sendHello(update);
                    break;

                case "/bounty@QCCrypto_bot":
                    sendBounty();
                    break;

                case "/roadmap@QCCrypto_bot":
                    sendRoadMap();
                    break;

                case "/noiquy@QCCrypto_bot":
                    sendNoiQuy();
                    break;

                case "/thongtin@QCCrypto_bot":
                    sendThongTin();
                    break;

                case "/samchiprofile@QCCrypto_bot":
                    sendSamChi();
                    break;

                case "/start@LandmarkAsia_bot":
                    sendAllCommand(update);
                    break;

                case "/huongdan@QCCrypto_bot":
                    sendHuongDan();
                    break;

                case "/bounty":
                    sendBounty();
                    break;

                case "/roadmap":
                    sendRoadMap();
                    break;

                case "/noiquy":
                    sendNoiQuy();
                    break;

                case "/thongtin":
                    sendThongTin();
                    break;

                case "/start":
                    sendAllCommand(update);
                    break;

                case "/samchiprofile":
                    sendSamChi();
                    break;

                case "/huongdan":
                    sendHuongDan();
                    break;


                default:
                    break;
            }
        }

        else if(update.hasCallbackQuery()){
            String call_data = update.getCallbackQuery().getData();

            if (call_data.equals("btnBounty")) {
                sendBounty();
            }
            else if (call_data.equals("btnLandmarkAsia")) {
                sendLandmarkAsia();
            }
            else if (call_data.equals("btnLandmarkCoin")) {
                sendLandmarkCoin();
            }
            else if (call_data.equals("btnBaoChi")) {
                sendBaoChi();
            }
            else if (call_data.equals("btnBountyLeader")) {
                sendBountyLeader();
            }
            else if (call_data.equals("btnYoutubeLink")) {
                sendYoutubeLink();
            }
            else if (call_data.equals("btnNoiQuy")) {
                sendNoiQuy();
            }
            else if (call_data.equals("btnRoadMap")) {
                sendRoadMap();
            }
            else if (call_data.equals("btnHuongDan")) {
                sendHuongDan();
            }
            else if (call_data.equals("btnSamChi")) {
                sendSamChi();
            }
            else if(call_data.equals("btnWhitePaper")){
                sendWhitePaper();
            }
        }
    }


    public void sendAllCommand(Update update){
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chat_id)
                .setText("Landmark Việt Nam Group");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline5 = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("LandmarkAsia").setCallbackData("btnLandmarkAsia"));
        rowInline.add(new InlineKeyboardButton().setText("Mr Sam Chi ").setCallbackData("btnSamChi"));
        rowInline1.add(new InlineKeyboardButton().setText("Landmark Coin").setCallbackData("btnLandmarkCoin"));
        rowInline1.add(new InlineKeyboardButton().setText("Hướng dẫn").setCallbackData("btnHuongDan"));
        rowInline2.add(new InlineKeyboardButton().setText("Báo chí").setCallbackData("btnBaoChi"));
        rowInline2.add(new InlineKeyboardButton().setText("Youtube").setCallbackData("btnYoutube"));
        rowInline3.add(new InlineKeyboardButton().setText("Bounty Leader").setCallbackData("btnBountyLeader"));
        rowInline3.add(new InlineKeyboardButton().setText("Bounty").setCallbackData("btnBounty"));
        rowInline4.add(new InlineKeyboardButton().setText("Roadmap").setCallbackData("btnRoadMap"));
        rowInline4.add(new InlineKeyboardButton().setText("Nội quy").setCallbackData("btnNoiQuy"));
        rowInline5.add(new InlineKeyboardButton().setText("White paper").setCallbackData("btnWhitePaper"));
        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);
        rowsInline.add(rowInline5);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void checkMessage(String command,Update update){
        command = command.toLowerCase();
        if (command.contains("scam") || command.contains("đa cấp") || command.contains("da cap") || command.contains("lua dao") || command.contains("lừa đảo")) {

            deleteMessage(update);
        }

        else if (command.contains(" joined the group")){
            sendHello(update);
        }

    }

    public void deleteMessage(Update update){
        System.out.println("Thằng nào đấy chat chữ scam kìa bot");
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chat_id);
        deleteMessage.setMessageId(update.getMessage().getMessageId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText("Chúng ta là người có học . Tại sao lại nói những từ thô tục vậy hả "+update.getMessage().getFrom().getFirstName()+" "+update.getMessage().getFrom().getLastName()+" ???");

        SendDocument sendAnimation = new SendDocument();
        sendAnimation.setChatId(chat_id);
        sendAnimation.setDocument("https://giphy.com/gifs/3o6Zt4CRe9MOLnYXss/html5");

        try {
            //execute(sendMessage);
            execute(deleteMessage);
            //(sendAnimation);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public Boolean isScamMessage(String message){
        return false;
    }

    public void editMessage(Update update,String message){
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chat_id);
        editMessageText.setMessageId(update.getMessage().getMessageId());
        editMessageText.setText(message);
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void replaceDirtywords(Update update,String message){
        if (message.contains("dm")){
            message=message.replace("dm","***");
        }
         if (message.contains("d m")){
             message=message.replace("d m","***");
        }
         if(message.contains("sex")){
             message=message.replace("d m","***");
        }
        editMessage(update,message);

    }

    public void sendBounty(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi thông tin bounty");
        sendMessage.setText(stringBounty);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendLoiKhuyen(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi lời khuyên");
        sendMessage.setText("Chúng ta là người có học. Đừng nói tục nữa các bạn :)");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendThongTin(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi thông tin");
        sendMessage.setText(stringThongTin);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendNoiQuy(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi nội quy");
        sendMessage.setText(stringNoiQuy);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendRoadMap(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi RoadMap");
        sendMessage.setText(stringRoadMap);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendHello(Update update){
        SendMessage sendMessage = new SendMessage();
        System.out.println(update.getMessage().getFrom().getFirstName()+" "+update.getMessage().getFrom().getLastName());
        sendMessage.setText("Chào mừng " + update.getMessage().getFrom().getFirstName()+" "+update.getMessage().getFrom().getLastName() + " đến với Landmark Việt Nam");
        sendMessage.setChatId(chat_id);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendSamChi(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi thông tin SamChi");
        sendMessage.setText(stringSamChi);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendHuongDan(){
        ForwardMessage forwardMessage = new ForwardMessage();
        forwardMessage.setChatId(chat_id);
        forwardMessage.setMessageId(3543);
        forwardMessage.setFromChatId(chat_id);

        try {
            execute(forwardMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendLandmarkAsia(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi thông tin Landmark");
        sendMessage.setText(stringLandmarkAsia);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendLandmarkCoin(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi thông tin Coin");
        sendMessage.setText(stringLandmarkCoin);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendBaoChi(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi thông tin bao chí");
        sendMessage.setText(stringBaoChi);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendBountyLeader(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi thông tin BountyLeader");
        sendMessage.setText(stringBountyLeader);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendYoutubeLink(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        System.out.println("Gửi thông tin Youtube");
        sendMessage.setText(stringYoutubeLink);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendWhitePaper(){
        ForwardMessage forwardMessage = new ForwardMessage();
        forwardMessage.setChatId(chat_id);
        forwardMessage.setMessageId(3548);
        forwardMessage.setFromChatId(chat_id);

        try {
            execute(forwardMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "LandmarkAsia_bot";
    }

    public String getBotToken() {
        return "botToken";
    }
}
