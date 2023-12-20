package com.example.mythic_vision_app;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mythic_vision_app.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;



public class MainActivity extends AppCompatActivity {
    String Balaji = "Balaji, also known as Lord Venkateswara or Lord Srinivasa, is a highly revered deity in Hinduism, particularly in the South Indian states of Andhra Pradesh and Tamil Nadu. He is considered an incarnation of Lord Vishnu, one of the principal deities in Hinduism. Here is a brief description of Lord Balaji:\n" +
            "\n" +
            "Appearance: Lord Balaji is typically depicted as a dark-complexioned deity with a serene and benevolent countenance. He is often portrayed standing upright on a lotus pedestal, with four hands holding various symbolic objects. He wears a crown and rich ornaments, emphasizing his divine and royal status.\n" +
            "\n" +
            "Symbolic Objects: In his four hands, Lord Balaji holds several symbolic objects:\n" +
            "\n" +
            "Sudarshana Chakra: This is a disc-like weapon that represents divine protection and the power to destroy ignorance.\n" +
            "Conch Shell (Shankha): The conch shell signifies the sound of the divine Aum (Om) and the call to awaken spiritual consciousness.\n" +
            "Mace (Gada): The mace symbolizes the power to control and protect the universe.\n" +
            "Lotus (Padma): The lotus is a symbol of purity and transcendence over worldly challenges.\n" +
            "Tirupati Temple: Lord Balaji is most famously worshipped at the Sri Venkateswara Temple in Tirupati, Andhra Pradesh. This temple is one of the most visited and revered pilgrimage sites in India. Devotees from all over the world come to offer their prayers and seek the blessings of Lord Balaji.\n" +
            "\n" +
            "Devotion and Seva: Lord Balaji is known for his immense compassion and accessibility to his devotees. The temple priests perform daily rituals and elaborate ceremonies to serve the deity, and devotees often express their devotion through acts of seva (service), such as offering food, contributing to charitable causes, and volunteering at the temple.\n" +
            "\n" +
            "Spiritual Significance: Lord Balaji is regarded as the preserver and protector of the world. Devotees believe that worshipping him can bring blessings, prosperity, and relief from life's challenges. Many people undertake a pilgrimage to Tirupati to seek his divine intervention in their lives.\n" +
            "\n" +
            "Festivals: The annual Brahmotsavam festival at the Tirupati Temple is one of the most significant events in Lord Balaji's worship. It lasts for nine days and includes grand processions, rituals, and celebrations.\n" +
            "\n" +
            "Universal Appeal: While Lord Balaji is primarily worshipped in South India, his universal appeal has led to devotees from diverse backgrounds and regions seeking his blessings. His devotees include people of all castes and communities.\n" +
            "\n" +
            "Philanthropic Activities: The Tirumala Tirupati Devasthanams (TTD), which manages the temple, is involved in numerous philanthropic activities, including free meals for pilgrims, education, healthcare, and disaster relief efforts.\n" +
            "\n" +
            "Devotees of Lord Balaji have deep faith in his grace and consider him a source of solace, protection, and spiritual guidance. His temple at Tirupati remains a symbol of devotion and is a testament to the enduring appeal of this beloved deity.";

    String DurgaMaa = "Durga Ma, often referred to simply as \"Durga,\" is a prominent and revered goddess in Hinduism. She is considered the embodiment of divine feminine energy and is celebrated as a symbol of strength, courage, and protection. Durga Ma is particularly significant in Hindu mythology and is worshipped in various forms across India and other parts of South Asia.\n" +
            "\n" +
            "Here is a brief description of Durga Ma:\n" +
            "\n" +
            "Goddess of Power and Shakti: Durga is often depicted as a powerful goddess who possesses immense strength and divine energy. She represents the primordial source of all power or \"Shakti\" in the universe.\n" +
            "\n" +
            "Protector and Warrior: Durga is known for her role as a fierce and valiant warrior who combats evil forces and demons. She is often depicted riding a lion or tiger, wielding weapons in her multiple arms, ready to battle any threat to the divine order.\n" +
            "\n" +
            "Iconic Image: Durga Ma is typically portrayed with multiple arms, each holding a weapon, symbolizing her ability to protect and maintain cosmic order. She has a serene yet determined expression on her face.\n" +
            "\n" +
            "Navaratri Festival: The festival of Navaratri, celebrated over nine nights, is dedicated to the worship of Durga Ma. It is one of the most widely observed festivals in India, and during this time, people pay homage to the goddess through prayer, music, dance, and rituals.\n" +
            "\n" +
            "Symbol of Feminine Power: Durga's portrayal as a powerful goddess emphasizes the strength and significance of feminine energy in Hinduism. She is a symbol of empowerment and serves as an inspiration for women and men alike.\n" +
            "\n" +
            "Many Forms: Durga has many forms and avatars, each with its own unique qualities and significance. Some popular forms of Durga include Mahakali, Parvati, and Ambika, among others.\n" +
            "\n" +
            "Cultural Significance: Durga Ma holds immense cultural importance in various regions of India. In West Bengal, she is celebrated with great enthusiasm during the festival of Durga Puja, marked by elaborate decorations, processions, and artistic representations of the goddess.\n" +
            "\n" +
            "Universal Mother: Durga is often referred to as the \"Universal Mother\" because of her nurturing and protective qualities. Devotees seek her blessings for courage, wisdom, and spiritual growth.\n" +
            "\n" +
            "In summary, Durga Ma is a revered goddess in Hinduism known for her embodiment of divine feminine energy, her role as a fierce protector, and her cultural significance in India. She represents strength, courage, and the nurturing aspect of the divine.";

    String Ganesha = "Lord Ganesha, often simply referred to as \"Ganesha\" or \"Ganpati,\" is one of the most beloved and widely worshiped deities in Hinduism. He is easily recognizable by his distinctive elephant-headed appearance and holds a special place in Hindu mythology and religious practices. Here is a brief description of Lord Ganesha:\n" +
            "\n" +
            "Elephant-Headed Deity: Lord Ganesha is depicted with the head of an elephant and a human body. This unique and iconic form symbolizes wisdom, intelligence, and the ability to overcome obstacles.\n" +
            "\n" +
            "Remover of Obstacles: Ganesha is revered as the \"Vighnaharta\" or \"Remover of Obstacles.\" Devotees often seek his blessings before embarking on any new endeavor, whether it's starting a business, taking an exam, or beginning a journey.\n" +
            "\n" +
            "God of Beginnings: He is considered the god of new beginnings, arts, sciences, and intellect. His presence is invoked to ensure a smooth start to any important undertaking.\n" +
            "\n" +
            "Symbol of Wisdom: Ganesha's large head signifies great wisdom and knowledge. His big ears represent the importance of listening and gaining wisdom from others.\n" +
            "\n" +
            "Four Arms: Ganesha is typically depicted with four arms. In one hand, he holds a modak (a sweet dumpling), symbolizing the rewards of spiritual pursuit. In another hand, he holds an ankusha (elephant goad), signifying his ability to steer people in the right direction. The third hand holds a noose, representing control over desires, and the fourth hand is raised in a gesture of blessing.\n" +
            "\n" +
            "Mouse as Vehicle: Ganesha's vahana (vehicle) is a mouse or rat. This symbolizes the importance of humility and the idea that wisdom should control one's ego, just as a small mouse is guided by Ganesha's wisdom.\n" +
            "\n" +
            "Festivals: The festival of Ganesh Chaturthi, dedicated to Lord Ganesha, is one of the most celebrated festivals in India. It involves elaborate idol installations, prayers, music, dance, and community gatherings. The festival lasts for ten days, culminating in the immersion of Ganesha idols in water bodies.\n" +
            "\n" +
            "Cultural Significance: Lord Ganesha is revered not only by Hindus but also by people of various cultural backgrounds and religions. His image and symbolism have transcended religious boundaries.\n" +
            "\n" +
            "Universal Appeal: Ganesha is regarded as a deity who is approachable and compassionate. Many devotees consider him a friend and guide in their daily lives.\n" +
            "\n" +
            "In summary, Lord Ganesha is a beloved deity in Hinduism known for his role as the Remover of Obstacles, his wisdom, and his association with new beginnings. His image, symbolism, and teachings hold universal appeal and have made him a cherished figure in Indian culture and beyond.";

    String Hanuman = "Lord Hanuman, also known simply as \"Hanuman\" or \"Bajrangbali,\" is a prominent and revered deity in Hinduism. He is widely venerated for his unwavering devotion, strength, and selfless service to Lord Rama. Here is a brief description of Lord Hanuman:\n" +
            "\n" +
            "Monkey God: Lord Hanuman is often depicted as a monkey-faced deity, and he is one of the central characters in the Indian epic, the Ramayana. He is a symbol of physical strength, agility, and courage.\n" +
            "\n" +
            "Devotee of Lord Rama: Hanuman's foremost attribute is his unwavering devotion to Lord Rama, the seventh avatar of Lord Vishnu. His devotion is so profound that he is often referred to as \"Ram Bhakt Hanuman.\"\n" +
            "\n" +
            "Symbol of Selfless Service: Hanuman's life is a testament to selfless service. He played a pivotal role in the rescue of Lord Rama's wife, Sita, from the demon king Ravana, and in the epic battle against evil forces in the Ramayana.\n" +
            "\n" +
            "Ability to Change Size: In Hindu mythology, Hanuman possesses the unique ability to change his size at will. He once used this power to shrink down and enter Lord Rama's heart to express his boundless devotion.\n" +
            "\n" +
            "Loyalty and Devotion: Hanuman's loyalty to Lord Rama is unparalleled. He is considered a role model for unwavering devotion and loyalty in Hinduism.\n" +
            "\n" +
            "Flying Capability: In the Ramayana, Hanuman leaped across the ocean to reach the island of Lanka (modern-day Sri Lanka) in search of Sita. His ability to fly is often associated with his strength and divine powers.\n" +
            "\n" +
            "Chiranjivi (Immortal Being): Hanuman is believed to be a chiranjivi, an immortal being who will live on Earth until the end of the current cosmic cycle.\n" +
            "\n" +
            "Worship and Festivals: Hanuman is widely worshiped across India and other parts of the world with temples dedicated to him. Hanuman Jayanti, his birthday, is a major festival celebrated with fervor and devotion.\n" +
            "\n" +
            "Symbol of Protection: Devotees often turn to Lord Hanuman for protection from harm, danger, and adversities. His blessings are sought to overcome obstacles in life.\n" +
            "\n" +
            "Chants and Mantras: The chanting of Hanuman Chalisa, a devotional hymn dedicated to Hanuman, is a common practice among his devotees. It is believed to bring strength, courage, and blessings.\n" +
            "\n" +
            "Cultural Significance: Hanuman's character and qualities have had a profound influence on Indian culture, literature, and art. His stories and teachings inspire people to lead a life of righteousness and devotion.\n" +
            "\n" +
            "In summary, Lord Hanuman is a revered deity in Hinduism known for his unwavering devotion to Lord Rama, his selfless service, and his immense strength. He serves as an embodiment of loyalty, courage, and devotion, and his worship is a source of inspiration for millions of people.";

    String Kali_Maa = "Goddess Kali, often simply referred to as \"Kali,\" is a powerful and complex deity in Hinduism. She is primarily associated with destruction, but her role goes far beyond that. Here is a brief description of Goddess Kali:\n" +
            "\n" +
            "Goddess of Time and Change: Kali's name is derived from the Sanskrit word \"kala,\" which means time. She is often seen as the personification of time, representing the ever-changing nature of existence.\n" +
            "\n" +
            "Fierce and Terrifying Form: Kali is usually depicted as a fierce and formidable goddess with a dark complexion. She is often portrayed with a garland of human skulls, a severed head in one hand, and a weapon in another. This fierce form represents her role as a destroyer of evil forces.\n" +
            "\n" +
            "Destroyer of Evil: Kali is a symbol of the destruction of ignorance, ego, and negative tendencies. She annihilates evil and protects her devotees from harm. Her ferocity is seen as a means to combat negativity and chaos.\n" +
            "\n" +
            "Mother Goddess: Despite her terrifying appearance, Kali is also seen as a compassionate mother figure. She is often depicted with a protruding tongue, a symbol of her thirst to lick away the sorrows of her children.\n" +
            "\n" +
            "Shakti (Divine Feminine Power): Kali is considered one of the forms of Shakti, the divine feminine energy that represents creation, preservation, and destruction. She embodies the power of transformation and change.\n" +
            "\n" +
            "Tantric Worship: Kali is a central figure in Tantric traditions, where she represents the transformative aspect of spiritual practices. Tantric worship of Kali involves rituals that aim to transcend the dualities of life and death.\n" +
            "\n" +
            "Symbol of Liberation: Kali's devotees seek her blessings for spiritual liberation (moksha) and the removal of obstacles on the path to enlightenment. She is seen as the liberator who frees the soul from the cycle of birth and death.\n" +
            "\n" +
            "Festivals: Kali is worshiped during various festivals, with Kali Puja being one of the most significant. This festival is celebrated with devotion and fervor, particularly in West Bengal, India.\n" +
            "\n" +
            "Cultural Influence: Kali has had a significant impact on art, literature, and culture. She is often portrayed in various art forms, including paintings and sculptures, and has been a subject of fascination and reverence for artists and writers.\n" +
            "\n" +
            "Spiritual Significance: Devotees of Kali seek her guidance and protection in times of crisis. Her worship is believed to bring strength, courage, and the ability to overcome obstacles.\n" +
            "\n" +
            "In summary, Goddess Kali is a multifaceted deity in Hinduism. She represents the destructive aspect of the divine, but her destruction is seen as a means of clearing the path for transformation and growth. She is both a fearsome and compassionate goddess, embodying the cycles of time and change in the universe.";

    String Khatu_Shyam = "Lord Khatu Shyam, also known as Shyam Baba, is a revered deity worshipped in Hinduism, particularly in the northern regions of India. He is considered to be a divine manifestation of Lord Krishna and is highly venerated by devotees. Here's a brief description of Lord Khatu Shyam:\n" +
            "\n" +
            "Origin: The worship of Khatu Shyam originated in the village of Khatu in the Sikar district of Rajasthan, India. The temple dedicated to him, known as the \"Shri Shyam Mandir,\" is one of the most famous pilgrimage sites in North India.\n" +
            "\n" +
            "Appearance: Lord Khatu Shyam is typically depicted as a young boy with dark-blue or black skin, similar to Lord Krishna. He is adorned with a crown and various ornaments, and he holds a flute and a stick (lathi) in his hands.\n" +
            "\n" +
            "Devotion and Faith: Devotees of Khatu Shyam are known for their deep devotion and unwavering faith. Many believe that he has the power to fulfill their wishes and desires.\n" +
            "\n" +
            "Miracles and Legends: Several miraculous stories and legends are associated with Khatu Shyam. Devotees believe that he has the ability to perform miracles and protect them from harm.\n" +
            "\n" +
            "Khatu Shyam Jayanti: The birth anniversary of Lord Khatu Shyam, known as \"Khatu Shyam Jayanti,\" is celebrated with great enthusiasm by his devotees. It falls on the Ekadashi (eleventh day) of the Krishna Paksha (dark fortnight) in the Hindu month of Phalgun.\n" +
            "\n" +
            "Bhajans and Kirtans: Devotional songs (bhajans) and kirtans (devotional chants) in praise of Lord Khatu Shyam are an integral part of the worship and festivities associated with him.\n" +
            "\n" +
            "Charitable Activities: Many devotees and organizations associated with Khatu Shyam engage in charitable activities, such as organizing free food distribution (prasad) for pilgrims and those in need.\n" +
            "\n" +
            "Pilgrimage: The Shri Shyam Mandir in Khatu, Rajasthan, is the primary pilgrimage destination for devotees of Lord Khatu Shyam. It attracts a large number of devotees from across India and even from abroad.\n" +
            "\n" +
            "Universal Appeal: The worship of Khatu Shyam transcends regional and cultural boundaries, drawing devotees from diverse backgrounds who come seeking his blessings.\n" +
            "\n" +
            "Teachings: While Khatu Shyam is primarily revered for his divine attributes and the fulfillment of devotees' wishes, his stories also contain moral and spiritual lessons similar to those found in the life and teachings of Lord Krishna.\n" +
            "\n" +
            "In essence, Lord Khatu Shyam is a beloved deity associated with faith, devotion, and the fulfillment of devotees' prayers and wishes. His temple in Khatu, Rajasthan, remains a center of spirituality and devotion, where thousands of pilgrims gather to seek his blessings and experience his divine presence.";

    String Krishna = "Lord Krishna, one of the most revered deities in Hinduism, is known for his multifaceted and divine attributes. He plays a central role in the Hindu epic, the \"Mahabharata,\" and is considered the eighth incarnation (avatar) of Lord Vishnu. Here's a brief description of Lord Krishna:\n" +
            "\n" +
            "Birth and Early Life: Lord Krishna was born in the Dwapara Yuga (an age in Hindu cosmology) to Queen Devaki and King Vasudeva in the city of Mathura. His birth took place in a prison cell, and he miraculously survived numerous attempts on his life by the tyrannical King Kansa, who was his maternal uncle.\n" +
            "\n" +
            "Divine Appearance: Lord Krishna is often depicted with a dark-blue or blackish complexion, and he is known for his enchanting beauty. He is typically portrayed as a young man playing a flute (the divine instrument associated with him).\n" +
            "\n" +
            "Flute Player: Krishna's divine music on the flute is believed to captivate the hearts of all living beings. It symbolizes the call of the divine, drawing devotees toward spiritual enlightenment.\n" +
            "\n" +
            "Leelas (Divine Play): Lord Krishna is famous for his divine play and pastimes, known as \"leelas.\" These include his childhood escapades, his role as a cowherd in Vrindavan, and his association with the Gopis (cowherd maidens), particularly his beloved Radha.\n" +
            "\n" +
            "Teachings in the Bhagavad Gita: Lord Krishna imparts profound spiritual wisdom to the warrior prince Arjuna on the battlefield of Kurukshetra in the form of the \"Bhagavad Gita.\" His teachings encompass various aspects of life, duty, righteousness, and the path to spiritual realization.\n" +
            "\n" +
            "Karma Yoga: In the Bhagavad Gita, Lord Krishna emphasizes the concept of \"Karma Yoga,\" which is the path of selfless action and performing one's duties without attachment to the results.\n" +
            "\n" +
            "Devotion and Bhakti: Krishna is also the focal point of the \"Bhakti\" (devotional) movement in Hinduism. Devotees across the world express their love and devotion to him through prayer, bhajans (devotional songs), and acts of devotion.\n" +
            "\n" +
            "Janmashtami: The birth anniversary of Lord Krishna, known as \"Janmashtami,\" is a major Hindu festival celebrated with great enthusiasm. Devotees fast, sing devotional songs, and participate in cultural performances to commemorate this auspicious day.\n" +
            "\n" +
            "Universal Appeal: Lord Krishna's teachings and leelas have a universal appeal that transcends religious and cultural boundaries. His message of love, compassion, and devotion resonates with people of all backgrounds.\n" +
            "\n" +
            "Icon of Divine Love: Krishna's love for Radha and the Gopis is often interpreted as the embodiment of divine love. Their stories symbolize the soul's longing for union with the divine.\n" +
            "\n" +
            "Various Forms and Avatars: Lord Krishna is worshipped in various forms and avatars, such as Lord Venkateswara in Tirupati and Lord Jagannath in Puri. Each form highlights different aspects of his divinity.\n" +
            "\n" +
            "Protector and Guide: Lord Krishna is regarded as the protector and spiritual guide of his devotees. He offers solace and guidance to those who seek refuge in him.\n" +
            "\n" +
            "In Hinduism, Lord Krishna is a beloved deity who represents the highest ideals of righteousness, devotion, and spirituality. His life and teachings continue to inspire countless individuals on their spiritual journeys, making him a timeless and revered figure in Indian culture and beyond.";
    String Sai_Baba = "Sai Baba, also known as Shirdi Sai Baba, is a revered and beloved spiritual figure in India, particularly within the state of Maharashtra. He is venerated by people of various religious backgrounds, including Hindus and Muslims. Sai Baba's life and teachings emphasize principles of love, compassion, and devotion. Here is a brief description of Sai Baba:\n" +
            "\n" +
            "Early Life: Sai Baba's exact date and place of birth are not well-documented, but it is believed that he was born in the late 19th century. He spent much of his life in the town of Shirdi, located in the state of Maharashtra, India.\n" +
            "\n" +
            "Mysterious Origins: Sai Baba's early life remains shrouded in mystery. He arrived in Shirdi as a young man and lived there for most of his life, performing acts of kindness and charity.\n" +
            "\n" +
            "Ascetic Lifestyle: Sai Baba lived a simple and ascetic life. He wore old, torn clothes and often begged for alms. Despite his humble appearance, he was known for his radiant and compassionate presence.\n" +
            "\n" +
            "Miracles and Healing: Sai Baba was renowned for performing miracles and healing the sick. His devotees believed that he possessed divine powers and that he could cure physical and spiritual ailments.\n" +
            "\n" +
            "Interfaith Figure: Sai Baba's teachings transcended religious boundaries. He welcomed people of all faiths into his fold and emphasized the universality of God. His mosque and the temple adjacent to it symbolize his inclusive approach.\n" +
            "\n" +
            "Teachings: Sai Baba's core teachings revolved around faith, love, and devotion to God. He often used parables and simple stories to convey profound spiritual messages. He encouraged his followers to lead a life of righteousness and to help those in need.\n" +
            "\n" +
            "Famous Sayings: Some of Sai Baba's famous sayings include \"Sabka Malik Ek\" (One God governs all) and \"Shraddha Saburi\" (Faith and patience). These sayings capture the essence of his teachings.\n" +
            "\n" +
            "Devotees: Sai Baba attracted a large following during his lifetime. His devotees came from various walks of life, and many sought his blessings for personal and spiritual reasons.\n" +
            "\n" +
            "Samadhi: Sai Baba left his mortal body on October 15, 1918. His devotees constructed a shrine (samadhi) in his honor at Shirdi, which has since become a place of pilgrimage and devotion.\n" +
            "\n" +
            "Festivals: Two major festivals associated with Sai Baba are Ram Navami and Guru Purnima. These festivals are celebrated with great fervor by his devotees.\n" +
            "\n" +
            "Legacy: Sai Baba's teachings and presence continue to influence the lives of millions of people. His philosophy of love, service, and faith has left a lasting impact, and his devotees worldwide seek solace and guidance through his teachings.\n" +
            "\n" +
            "Places of Worship: Besides the shrine at Shirdi, there are Sai Baba temples and centers across the globe where devotees gather for prayers and bhajans (devotional songs).\n" +
            "\n" +
            "Sai Baba's life exemplified the ideals of selfless service and devotion to God. His teachings promote unity, love, and harmony among people of different faiths and backgrounds. He remains an enduring spiritual figure whose legacy continues to inspire and uplift the hearts of those who seek his blessings and guidance.";
    String Saraswati = "Saraswati is a prominent and revered goddess in Hinduism. She is often considered the embodiment of knowledge, wisdom, creativity, music, arts, and learning. Saraswati is celebrated for her role in bestowing knowledge and eloquence upon her devotees. Here is a brief description of Saraswati:\n" +
            "\n" +
            "Appearance: Saraswati is typically depicted as a graceful and serene goddess, often dressed in white attire, symbolizing purity and knowledge. She is adorned with various symbols, including a veena (a musical instrument), a book, and a swan.\n" +
            "\n" +
            "Iconography:\n" +
            "\n" +
            "Veena: Saraswati is commonly portrayed holding a veena, a musical instrument, representing her association with the arts and creativity.\n" +
            "Book: She is also depicted with a sacred book, symbolizing knowledge and wisdom.\n" +
            "Swan: Saraswati is sometimes shown riding on a swan, a symbol of grace and discernment.\n" +
            "Meaning of the Name: The name \"Saraswati\" is derived from the Sanskrit words \"saras,\" which means \"flow,\" and \"wati,\" which means \"a woman.\" Thus, Saraswati is often associated with the flowing and continuous stream of knowledge and wisdom.\n" +
            "\n" +
            "Goddess of Learning: Saraswati is considered the goddess of learning, education, and academic excellence. Students, scholars, and artists seek her blessings for success in their endeavors.\n" +
            "\n" +
            "Consort of Brahma: In Hindu mythology, Saraswati is regarded as the consort of Lord Brahma, the creator of the universe. Together, they are part of the holy trinity of Hinduism, which includes Brahma (the creator), Vishnu (the preserver), and Shiva (the destroyer).\n" +
            "\n" +
            "Festivals: The festival of Saraswati Puja is celebrated with great enthusiasm in various parts of India, particularly in educational institutions. It usually falls in late January or early February. Devotees worship Saraswati by offering prayers, flowers, and sweets, and by seeking her blessings for academic success.\n" +
            "\n" +
            "Role in Hindu Pantheon: Saraswati plays a vital role in the Hindu pantheon and is considered one of the most benevolent and revered deities. She is associated with the pursuit of knowledge, arts, and culture.\n" +
            "\n" +
            "Universal Wisdom: Saraswati's significance transcends religious boundaries. Her portrayal as the goddess of knowledge highlights the universal value of education and intellectual pursuits.\n" +
            "\n" +
            "Mantras: Devotees often recite Saraswati mantras and prayers to invoke her blessings for clarity of thought, creativity, and eloquence.\n" +
            "\n" +
            "Cultural Influence: Saraswati's influence extends to various aspects of Indian culture, including classical music, dance, literature, and art. She is an inspiration to artists, musicians, and scholars.\n" +
            "\n" +
            "Goddess of Speech: Saraswati is also associated with the power of speech and eloquence. Her blessings are sought to enhance one's ability to communicate effectively.\n" +
            "\n" +
            "Saraswati embodies the ideals of knowledge, creativity, and wisdom. She serves as a source of inspiration for those who seek to expand their horizons through learning and artistic expression. Saraswati's presence in Hinduism reflects the profound respect and reverence for the pursuit of knowledge and the enrichment of human consciousness through education and culture.";
    String Shiva = "Lord Shiva, one of the principal deities of Hinduism, is often referred to as the \"Destroyer\" within the Holy Trinity of deities, which also includes Brahma, the \"Creator,\" and Vishnu, the \"Preserver.\" Here's a brief description of Lord Shiva:\n" +
            "\n" +
            "Appearance: Lord Shiva is typically depicted as a yogi with ash smeared on his body, often adorned with a snake as a necklace, a crescent moon on his head, a third eye on his forehead, and a trident (trishul) in his hand. He has a blue throat, earning him the name \"Neelakantha\" (the one with a blue throat), which he acquired after drinking the poison that emerged during the churning of the ocean (Samudra Manthan) to save the universe.\n" +
            "\n" +
            "Roles and Attributes:\n" +
            "\n" +
            "The Destroyer: Shiva's role as the \"Destroyer\" signifies the cyclical nature of creation, preservation, and destruction in the universe. He destroys the old to make way for the new, allowing for renewal and transformation.\n" +
            "The Ascetic: Shiva is also known as the supreme ascetic, symbolizing self-discipline, meditation, and detachment from worldly desires. He represents the path to spiritual realization through meditation and inner reflection.\n" +
            "Lord of Dance (Nataraja): Shiva is often depicted performing the Tandava, a cosmic dance that symbolizes both the creation and destruction of the universe. In this form, he is called Nataraja, the \"Lord of Dance.\"\n" +
            "Family and Consort:\n" +
            "\n" +
            "Parvati: Goddess Parvati, an incarnation of Shakti (the divine feminine energy), is Shiva's consort and wife. Together, they represent the divine union of male and female energies.\n" +
            "Sons: Shiva and Parvati have two sons: Ganesha, the elephant-headed god, and Kartikeya, the commander of the celestial army.\n" +
            "Symbolism:\n" +
            "\n" +
            "Crescent Moon: The crescent moon on Shiva's head represents the passing of time and the lunar cycle.\n" +
            "Snake: The snake around his neck signifies his mastery over fear and desire.\n" +
            "Trident (Trishul): The trident symbolizes the three fundamental aspects of existence: creation, preservation, and destruction.\n" +
            "Third Eye: Shiva's third eye represents wisdom, knowledge, and insight. When opened, it can emit destructive energy.\n" +
            "River Ganga: Lord Shiva is often depicted with the river Ganga flowing from his matted hair, symbolizing the purifying and life-giving aspects of water.\n" +
            "Worship and Festivals:\n" +
            "\n" +
            "Maha Shivaratri: This is one of the most important festivals dedicated to Lord Shiva. Devotees fast and offer prayers to seek his blessings on this day.\n" +
            "Lingam Worship: The Shiva Lingam, a phallic symbol representing Shiva's creative energy, is worshipped in temples across India.\n" +
            "Philosophical Significance: Shiva is associated with various philosophical schools, including Shaivism and Tantra. His meditative and ascetic qualities reflect the pursuit of spiritual knowledge and liberation from the cycle of birth and death (samsara).\n" +
            "\n" +
            "Universal Appeal: While deeply rooted in Hinduism, Lord Shiva's symbolism and teachings have universal appeal, transcending religious boundaries. Many seekers and yogis revere Shiva as a source of inspiration for meditation and spiritual awakening.\n" +
            "\n" +
            "Lord Shiva's multifaceted nature encompasses both the destructive and benevolent aspects of existence. His worship is an expression of devotion, self-realization, and the pursuit of ultimate truth and liberation in Hindu spirituality.";

    Button camera, gallery;
    ImageView imageView;
    TextView result;
    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        camera = findViewById(R.id.button);
        gallery = findViewById(R.id.button2);

        result = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);

        camera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 3);
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent, 1);
            }
        });
    }

    public void classifyImage(Bitmap image){
        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());
            inputFeature0.loadBuffer(byteBuffer);

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
            for(int i = 0; i < imageSize; i ++){
                for(int j = 0; j < imageSize; j++){
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 1));
                }
            }

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();

            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"Balaji", "Durga Maa", "Ganesha", "Hanuman", "Kali Maa", "Khatu Shyam", "Krishna", "Sai Baba", "Saraswati", "Shiva"};
            result.setText(classes[maxPos]);

            if(maxPos == 0){
                result.setText(Balaji);
            }else if(maxPos == 1){
                result.setText(DurgaMaa);
            }else if(maxPos == 2){
                result.setText(Ganesha);
            }else if(maxPos == 3){
                result.setText(Hanuman);
            }else if(maxPos == 4){
                result.setText(Kali_Maa);
            }else if(maxPos == 5){
                result.setText(Khatu_Shyam);
            }else if(maxPos == 6){
                result.setText(Krishna);
            }else if(maxPos == 7){
                result.setText(Sai_Baba);
            }else if(maxPos == 8){
                result.setText(Saraswati);
            }else{
                result.setText(Shiva);
            }
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 3){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }else{
                Uri dat = data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}