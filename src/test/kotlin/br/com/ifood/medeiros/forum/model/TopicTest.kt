package br.com.ifood.medeiros.forum.model

object TopicTest {
    fun build() = Topic(
        id = 1,
        title = "Course Name",
        message = "Description",
        course = CourseTest.build(),
        author = UserTest.build(),
    )
}
