# Тестовое задание для Android-разработчика
<div><a href="https://www.figma.com/file/8FvAWXCD2oD9oSDHx9xFfU/Тестовое-задание-Android?type=design&node-id=0-1&mode=design&t=8ZuZBEgxuMHAqrpe-0">Ссылка на дизайн</a></div>
<div><a href="https://www.themealdb.com/api.php">Ссылка на API</a></div>

<div><h2>Что надо сделать:</h2></div>
<div>
  <ul>
		<li type="disc">Главный экран приложения доставки еды, дизайн по ссылке выше</li>
    <li type="disc">В баннеры можно захардкодить любые фото</li>
    <li type="disc">Основная задача - сделать идентичную планку с категориями и блок меню(Подсказка. Для реализации можно использовать CollapsingToolbarLayout)</li>
    <li type="disc">Планка с категориями при скролле должна прилипать к верхнему бару(для примера можно посмотреть приложение Додо Пицца).</li>
    <li type="disc">В качестве API можно использовать данную API </li>
    <ul>
      <li type="circle">Для загрузки списка продукции <a href="и themealdb.com/api/json/v1/1/search.php?s">и themealdb.com/api/json/v1/1/search.php?s</a></li>
      <li type="circle">Для загрузки списка категорий (тегов) <a href=" themealdb.com/api/json/v1/1/categories.php"> themealdb.com/api/json/v1/1/categories.php</a></li>
      <li type="circle">Фильтровать продукцию можно по параметру strCategory на клиенте, без дополнительных сетевых запросов</li>
    </ul></li>
    <li type="disc">Offline-режим: т.е. в случае, если нет доступа к сети, показывать последние загруженные данные(и ленту, и детали)</li>
	</ul>
</div>

<div><h2>Ограничения на стек технологий:</h2></div>
<div>
  <ul>
		<li type="disc">MVVM</li>
    <li type="disc">Clean Architecture</li>
    <li type="disc">Остальное на ваше усмотрение</li>
  </ul>
</div>
